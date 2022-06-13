package com.example.githubusers

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @GET("/users")
    fun index(): Call<List<Posts>>
    @GET("/users/{userName}")
    fun getUser(@Path("userName") name: String): Call<Posts>
}//ApiService