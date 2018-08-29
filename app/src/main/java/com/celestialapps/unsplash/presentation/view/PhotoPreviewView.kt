package com.celestialapps.unsplash.presentation.view

import android.graphics.Bitmap

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface PhotoPreviewView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun loadPhotoAsyncSuccess(bitmap: Bitmap)

    @StateStrategyType(SkipStrategy::class)
    fun loadPhotoAsyncFailed(message: String)
}
