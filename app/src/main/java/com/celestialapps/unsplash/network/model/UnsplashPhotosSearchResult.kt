package com.celestialapps.unsplash.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UnsplashPhotosSearchResult {

    @SerializedName("total")
    @Expose
    var total: Long = 0
    @SerializedName("total_pages")
    @Expose
    var totalPages: Long = 0
    @SerializedName("results")
    @Expose
    var results: List<Result>? = null
}
