package com.example.githubusers.mode

import com.google.gson.annotations.SerializedName

class Posts {
    @SerializedName("login")
    var login: String? = null
    @SerializedName("twitter_username")
    var twitterUsername: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("location")
    var location: String? = null
    @SerializedName("blog")
    var blog: String? = null
    @SerializedName("avatar_url")
    var avatarUrl: String? = null
    @SerializedName("bio")
    var bio: String? = null
}//Posts
