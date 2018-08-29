package com.celestialapps.unsplash.dager.modules

import android.content.Context
import android.util.Pair

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory

import java.io.File

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    private val baseUrl = "https://api.unsplash.com/"

    private val clientIdPair = Pair("client_id", "e0c91ff612c0e3b24a586d6ff46f1e5cb1ca8100aa56724cfd42c2e45cbb2ae1")


    @Provides
    @Singleton
    fun provideGsonRetrofit(convectorFactory: Converter.Factory,
                            callAdapterFactory: CallAdapter.Factory,
                            okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(convectorFactory)
                .build()
    }

    @Provides
    @Singleton
    fun provideCoroutineCallAdapterFactory() : CallAdapter.Factory {
        return CoroutineCallAdapterFactory.invoke();
    }


    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }


    @Provides
    @Singleton
    @Named("ClientIdInterceptor")
    fun provideClientIdInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder().addQueryParameter(clientIdPair.first, clientIdPair.second).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    @Named("HttpLoggingInterceptor")
    fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    @Named("CacheControlInterceptor")
    fun provideCacheControlInterceptor(): Interceptor {
        return Interceptor{ chain ->
            val originalResponse = chain.proceed(chain.request())
            val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
            originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
        }
    }

    @Provides
    @Singleton
    fun provideGsonOkHttpClient(@Named("HttpLoggingInterceptor") httpLoggingInterceptor: Interceptor,
                                @Named("ClientIdInterceptor") clientIdInterceptor: Interceptor,
                                @Named("CacheControlInterceptor") cacheControlInterceptor: Interceptor,
                                cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(clientIdInterceptor)
                .addNetworkInterceptor(cacheControlInterceptor)
                .cache(cache)
                .build()
    }


    @Provides
    @Singleton
    fun provideCache(file: File): Cache {
        return Cache(file, (10 * 1024 * 1024).toLong()) // 10 MiB
    }

    @Provides
    @Singleton
    fun provideHttpCacheDirectory(context: Context): File {
        return File(context.cacheDir, "responses")
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
    }

}
