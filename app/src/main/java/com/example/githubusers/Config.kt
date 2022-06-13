package com.example.githubusers

import android.content.Context

object Config {
    const val gitHubUsrsListURL = "https://api.github.com/users"
    const val url = "https://api.github.com"
    const val gitHubUserDetailURL = "https://api.github.com/users/"
    var userName = ""
    lateinit var mainActivityContext : Context
}//Config