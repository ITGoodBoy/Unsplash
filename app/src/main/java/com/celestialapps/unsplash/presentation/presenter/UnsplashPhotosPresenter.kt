package com.celestialapps.unsplash.presentation.presenter

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.celestialapps.unsplash.App
import com.celestialapps.unsplash.R
import com.celestialapps.unsplash.network.api.UnsplashApi
import com.celestialapps.unsplash.network.model.UnsplashPhotos
import com.celestialapps.unsplash.network.model.UnsplashPhotosSearchResult
import com.celestialapps.unsplash.presentation.view.UnsplashPhotosView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.Response
import javax.inject.Inject

@InjectViewState
class UnsplashPhotosPresenter : MvpPresenter<UnsplashPhotosView>() {

    @Inject
    lateinit var mUnsplashApi: UnsplashApi
    @Inject
    lateinit var mContext: Context

    init {
        App.appComponent?.inject(this)
    }

    fun getNextPagePhotos(page: Int, countPerPage: Int) {
        launch(UI) {
            try {
                val response: Response<List<UnsplashPhotos>> = mUnsplashApi.getPhotos(page, countPerPage).await()
                if (response.isSuccessful && response.body() != null) {
                    viewState.gettingUnsplashPhotosNextPageSuccess(response.body()!!, page)
                } else {
                    viewState.gettingUnsplashPhotosNextPageFailed(mContext.getString(R.string.server_error))
                }
            } catch (exception: Exception) {
                viewState.gettingUnsplashPhotosNextPageFailed(mContext!!.getString(R.string.not_internet))
            }
        }
    }

    fun searchNextPagePhotos(query: String, page: Int, countPerPage: Int) {
        launch(UI) {
            try {

                val response: Response<UnsplashPhotosSearchResult> = mUnsplashApi.searchPhotos(query, page, countPerPage).await()
                if (response.isSuccessful && response.body() != null) {
                    viewState.searchPhotosNextPageSuccess(response.body()!!, query, page)
                } else {
                    viewState.searchPhotosNextPageFailed(mContext.getString(R.string.server_error))
                }
            } catch (exception: Exception) {
                viewState.searchPhotosNextPageFailed(mContext!!.getString(R.string.not_internet))
            }
        }
    }
}