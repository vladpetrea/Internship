package com.example.myapplication

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ChuckNorisService {

    @GET("/jokes/random")
    suspend fun getChuckJoke(
        @Query("category") category: String,
        @Header("key") value: String
    ): ResponseBody

    @GET("/jokes/random")
    suspend fun getChuckJokeParsed(
        @Query("category") category: String
    ): ChuckJokeModel


}
