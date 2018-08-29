package com.celestialapps.unsplash.network.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Urls(@SerializedName("raw") @Expose var raw: String,
                @SerializedName("full") @Expose var full: String,
                @SerializedName("regular") @Expose var regular: String,
                @SerializedName("small") @Expose var small: String,
                @SerializedName("thumb") @Expose var thumb: String) : Parcelable {

}
