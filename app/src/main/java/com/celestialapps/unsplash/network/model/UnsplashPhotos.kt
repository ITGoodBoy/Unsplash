package com.celestialapps.unsplash.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UnsplashPhotos {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
    @SerializedName("width")
    @Expose
    var width: Int = 0
    @SerializedName("height")
    @Expose
    var height: Int = 0
    @SerializedName("color")
    @Expose
    var color: String? = null
    @SerializedName("urls")
    @Expose
    var urls: Urls? = null
    @SerializedName("links")
    @Expose
    var links: Links? = null
    @SerializedName("sponsored")
    @Expose
    var sponsored: Boolean = false
    @SerializedName("likes")
    @Expose
    var likes: Int = 0
    @SerializedName("liked_by_user")
    @Expose
    var likedByUser: Boolean = false
    @SerializedName("user")
    @Expose
    var user: User? = null

    override fun toString(): String {
        return "UnsplashPhotos(id=$id, createdAt=$createdAt, updatedAt=$updatedAt, width=$width, " +
                "height=$height, color=$color, urls=$urls, links=$links, sponsored=$sponsored, " +
                "likes=$likes, likedByUser=$likedByUser, user=$user)"
    }


}