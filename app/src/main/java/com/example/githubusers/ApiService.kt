package com.example.githubusers

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    fun index(): Call<List<Posts>>
}//ApiService