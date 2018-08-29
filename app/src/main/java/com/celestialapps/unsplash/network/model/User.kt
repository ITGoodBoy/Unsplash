package com.celestialapps.unsplash.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("first_name")
    @Expose
    var firstName: String? = null
    @SerializedName("last_name")
    @Expose
    var lastName: String? = null
    @SerializedName("twitter_username")
    @Expose
    var twitterUsername: Any? = null
    @SerializedName("portfolio_url")
    @Expose
    var portfolioUrl: String? = null
    @SerializedName("bio")
    @Expose
    var bio: String? = null
    @SerializedName("location")
    @Expose
    var location: String? = null
    @SerializedName("links")
    @Expose
    var links: UserLinks? = null
    @SerializedName("profile_image")
    @Expose
    var profileImage: ProfileImage? = null
    @SerializedName("instagram_username")
    @Expose
    var instagramUsername: String? = null
    @SerializedName("total_collections")
    @Expose
    var totalCollections: Int = 0
    @SerializedName("total_likes")
    @Expose
    var totalLikes: Int = 0
    @SerializedName("total_photos")
    @Expose
    var totalPhotos: Int = 0

    override fun toString(): String {
        return "User(id=$id, updatedAt=$updatedAt, username=$username, name=$name, " +
                "firstName=$firstName, lastName=$lastName, twitterUsername=$twitterUsername, " +
                "portfolioUrl=$portfolioUrl, bio=$bio, location=$location, links=$links, " +
                "profileImage=$profileImage, instagramUsername=$instagramUsername, " +
                "totalCollections=$totalCollections, totalLikes=$totalLikes, totalPhotos=$totalPhotos)"
    }


}
