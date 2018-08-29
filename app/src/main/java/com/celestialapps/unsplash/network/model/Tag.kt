package com.celestialapps.unsplash.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tag {

    @SerializedName("title")
    @Expose
    var title: String? = null
}
