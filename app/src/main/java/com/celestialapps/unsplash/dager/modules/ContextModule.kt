package com.celestialapps.unsplash.dager.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val mContext: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return mContext
    }
}