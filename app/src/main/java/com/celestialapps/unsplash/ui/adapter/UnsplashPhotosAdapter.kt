package com.celestialapps.unsplash.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.celestialapps.unsplash.R
import com.celestialapps.unsplash.constant.ExtraConstants
import com.celestialapps.unsplash.glide.GlideApp
import com.celestialapps.unsplash.network.model.Result
import com.celestialapps.unsplash.network.model.UnsplashPhotos
import com.celestialapps.unsplash.ui.activity.PhotoPreviewActivity

import java.util.ArrayList

class UnsplashPhotosAdapter(private val mContext: Context,
                            private val mUnsplashPhotos: MutableList<UnsplashPhotos>) : RecyclerView.Adapter<UnsplashPhotosAdapter.ViewHolder>() {



    fun addItems(unsplashPhotos: List<UnsplashPhotos>) {
        mUnsplashPhotos.addAll(unsplashPhotos)
        notifyDataSetChanged()
    }

    fun addItemsFromSearch(unsplashPhotos: List<Result>) {

        val unsplashPhotosList = ArrayList<UnsplashPhotos>()

        for (result in unsplashPhotos) {
            val unsplashPhoto = UnsplashPhotos()
            unsplashPhoto.urls = result.urls

            unsplashPhotosList.add(unsplashPhoto)
        }

        mUnsplashPhotos.addAll(unsplashPhotosList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.photo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unsplashPhotos = mUnsplashPhotos[position]
        holder.mAcImv.setOnClickListener {
            mContext.startActivity(Intent(mContext, PhotoPreviewActivity::class.java)
                    .putExtra(ExtraConstants.EXTRA_URLS, unsplashPhotos.urls))
        }

        GlideApp.with(mContext)
                .load(unsplashPhotos.urls!!.small)
                .transform(CircleCrop())
                .placeholder(R.drawable.ic_load_photo)
                .into(holder.mAcImv)
    }

    override fun getItemCount(): Int {
        return mUnsplashPhotos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mAcImv: AppCompatImageView = itemView.findViewById(R.id.m_ac_imv)

    }
}
