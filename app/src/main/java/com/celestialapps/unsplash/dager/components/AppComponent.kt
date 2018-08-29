package com.celestialapps.unsplash.dager.components


import com.celestialapps.unsplash.dager.modules.ApiModule
import com.celestialapps.unsplash.dager.modules.ContextModule
import com.celestialapps.unsplash.dager.modules.RetrofitModule
import com.celestialapps.unsplash.presentation.presenter.PhotoPreviewPresenter
import com.celestialapps.unsplash.presentation.presenter.UnsplashPhotosPresenter

import javax.inject.Singleton

import dagger.Component


@Component(modules = [ContextModule::class, ApiModule::class, RetrofitModule::class])
@Singleton
interface AppComponent {
    fun inject(unsplashPhotosPresenter: UnsplashPhotosPresenter)

    fun inject(photoPreviewPresenter: PhotoPreviewPresenter)
}
