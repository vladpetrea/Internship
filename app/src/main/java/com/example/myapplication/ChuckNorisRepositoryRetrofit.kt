package com.example.myapplication

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Retrofit

class ChuckNorisRepositoryRetrofit {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    val service = retrofit.create(ChuckNorisService::class.java)

    suspend fun retrieveRandomChuckJokeWithQueryParams(): ResponseBody {
        val response = service.getChuckJoke("movie", "value")
        println("ChuckNorisRepositoryRetrofit:${response.string()}")
        return response
    }

    suspend fun retrieveChuckJokeParsed(): ChuckJokeModel {
        val chuckModel = service.getChuckJokeParsed("movie")
        println("ChuckNorisRepositoryRetrofit:${chuckModel}")
        return chuckModel
    }
}