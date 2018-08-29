package com.celestialapps.unsplash.ui.activity

import android.app.WallpaperManager
import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.AppCompatImageView
import android.view.View

import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.celestialapps.unsplash.R
import com.celestialapps.unsplash.constant.ExtraConstants
import com.celestialapps.unsplash.glide.GlideApp
import com.celestialapps.unsplash.network.model.Urls
import com.celestialapps.unsplash.presentation.presenter.PhotoPreviewPresenter
import com.celestialapps.unsplash.presentation.view.PhotoPreviewView
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import kotlinx.android.synthetic.main.activity_photo_preview.*

import java.io.IOException

class PhotoPreviewActivity : BaseActivity(), PhotoPreviewView {

    @InjectPresenter
    lateinit var mPhotoPreviewPresenter: PhotoPreviewPresenter

    private lateinit var mUrls: Urls

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_preview)

        mUrls = intent.getParcelableExtra(ExtraConstants.EXTRA_URLS)

        mPhotoPreviewPresenter.loadPhotoByUrl(mUrls.regular)
    }

    private fun setupSetWallpapersButton(bitmap: Bitmap) {
        m_ac_imv_set_wallpapers.visibility = View.VISIBLE
        m_ac_imv_set_wallpapers.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this@PhotoPreviewActivity)
                    .setTitle(R.string.dialog_wallpapers_title)
                    .setMessage(R.string.dialog_wallpapers_message)
                    .setNegativeButton(R.string.dialog_wallpapers_cancel, null)
                    .setPositiveButton(R.string.dialpg_wallpapers_set) { dialog, which ->
                        val myWallpaperManager = WallpaperManager.getInstance(applicationContext)
                        try {
                            myWallpaperManager.setBitmap(bitmap)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }.create()

            alertDialog.show()
        }
    }

    override fun loadPhotoAsyncSuccess(bitmap: Bitmap) {
        m_scale_imv_photo_preview.setImage(ImageSource.bitmap(bitmap))
        setupSetWallpapersButton(bitmap)
    }

    override fun loadPhotoAsyncFailed(message: String) {
        m_ac_imv_set_wallpapers.visibility = View.INVISIBLE
        m_ac_imv_set_wallpapers.setOnClickListener(null)

        getSnackBar(message).setAction(getString(R.string.retry)) {
            mPhotoPreviewPresenter.loadPhotoByUrl(mUrls.regular)
        }.show()
    }
}
