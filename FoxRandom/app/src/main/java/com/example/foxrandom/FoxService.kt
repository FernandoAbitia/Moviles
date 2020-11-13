package com.example.foxrandom

import retrofit2.Call
import retrofit2.http.GET

interface FoxService {

    @GET("floof")
    fun getRandomFox(): Call<Fox>
}