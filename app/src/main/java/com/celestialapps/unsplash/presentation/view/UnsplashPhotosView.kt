package com.celestialapps.unsplash.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.celestialapps.unsplash.network.model.UnsplashPhotos
import com.celestialapps.unsplash.network.model.UnsplashPhotosSearchResult

interface UnsplashPhotosView : MvpView {

    fun searchPhotosNextPageSuccess(unsplashPhotosSearchResult: UnsplashPhotosSearchResult,
                                    query: String, currentPage: Int)

    @StateStrategyType(SkipStrategy::class)
    fun searchPhotosNextPageFailed(message : String)

    fun gettingUnsplashPhotosNextPageSuccess(unsplashPhotosList: List<UnsplashPhotos>, currentPage : Int)

    @StateStrategyType(SkipStrategy::class)
    fun gettingUnsplashPhotosNextPageFailed(message : String)

}