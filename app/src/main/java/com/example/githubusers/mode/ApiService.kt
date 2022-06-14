package com.example.githubusers.mode

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users")
    fun index(): Call<List<Posts>>
    @GET("/users/{userName}")
    fun getUser(@Path("userName") name: String): Call<Posts>
}//ApiService