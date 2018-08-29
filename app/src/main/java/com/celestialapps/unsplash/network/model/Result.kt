package com.celestialapps.unsplash.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {

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
    var width: Long = 0
    @SerializedName("height")
    @Expose
    var height: Long = 0
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
    var likes: Long = 0
    @SerializedName("liked_by_user")
    @Expose
    var likedByUser: Boolean = false
    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("tags")
    @Expose
    var tags: List<Tag>? = null
    @SerializedName("photo_tags")
    @Expose
    var photoTags: List<PhotoTag>? = null

}
