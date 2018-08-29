package com.celestialapps.unsplash.network.api

import com.celestialapps.unsplash.network.model.UnsplashPhotos
import com.celestialapps.unsplash.network.model.UnsplashPhotosSearchResult
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("photos/")
    fun getPhotos(@Query("page") page: Int,
                  @Query("per_page") countPerPage: Int): Deferred<Response<List<UnsplashPhotos>>>


    @GET("search/photos")
    fun searchPhotos(@Query("query") query: String,
                     @Query("page") page : Int,
                     @Query("per_page") countPerPage: Int) : Deferred<Response<UnsplashPhotosSearchResult>>
}