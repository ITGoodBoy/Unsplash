package com.celestialapps.unsplash.presentation.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.celestialapps.unsplash.App
import com.celestialapps.unsplash.R
import com.celestialapps.unsplash.glide.GlideApp
import com.celestialapps.unsplash.presentation.view.PhotoPreviewView
import com.davemorrissey.labs.subscaleview.ImageSource

import javax.inject.Inject

@InjectViewState
class PhotoPreviewPresenter : MvpPresenter<PhotoPreviewView>() {

    @Inject
    lateinit var mContext: Context

    init {
        App.appComponent!!.inject(this)
    }

    fun loadPhotoByUrl(url: String) {

        GlideApp.with(mContext).asBitmap().load(url).listener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Bitmap>, isFirstResource: Boolean): Boolean {
                viewState.loadPhotoAsyncFailed(mContext!!.getString(R.string.not_internet))
                return true
            }

            override fun onResourceReady(resource: Bitmap, model: Any, target: Target<Bitmap>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                viewState.loadPhotoAsyncSuccess(resource)
                return true
            }
        }).submit()
    }
}
