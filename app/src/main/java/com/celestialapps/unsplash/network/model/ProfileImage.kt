package com.celestialapps.unsplash.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProfileImage {

    @SerializedName("small")
    @Expose
    var small: String? = null
    @SerializedName("medium")
    @Expose
    var medium: String? = null
    @SerializedName("large")
    @Expose
    var large: String? = null

    override fun toString(): String {
        return "ProfileImage(small=$small, medium=$medium, large=$large)"
    }


}
