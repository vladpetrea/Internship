package com.example.myapplication

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request


class ChuckNorisRepositoryOkHttp {

    fun retrieveRandomChuckJoke() {
        try {
            val client = OkHttpClient()
            val request: Request =
                Request.Builder().url("https://api.chucknorris.io/jokes/random")
                    .header("key", "value")
                    .build()
            val response = client.newCall(request).execute()
            println("ChuckNorisRepository:${response.body?.string()}")
        } catch (exception: Exception) {
            Log.e("ChuckNorisRepository", exception.toString())
        }
    }

    fun retrieveRandomChuckJokeWithQueryParams() {
        try {
            val httpUrl = HttpUrl.Builder().scheme("https")
                .host("api.chucknorris.io")
                .addPathSegment("jokes")
                .addPathSegment("random")
                .addQueryParameter("category", "movie")
                .build()
            val client = OkHttpClient()
            val request: Request =
                Request.Builder().url(httpUrl)
                    .header("key", "value")
                    .build()
            val response = client.newCall(request).execute()
            println("ChuckNorisRepository:${response.body?.string()}")
        } catch (exception: Exception) {
            Log.e("ChuckNorisRepository", exception.toString())
        }
    }

}