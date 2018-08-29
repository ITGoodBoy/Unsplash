package com.celestialapps.unsplash.network.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(@SerializedName("self") @Expose var self: String,
                 @SerializedName("html") @Expose var html: String,
                 @SerializedName("download") @Expose var download: String,
                 @SerializedName("download_location") @Expose var downloadLocation: String) : Parcelable {


}
