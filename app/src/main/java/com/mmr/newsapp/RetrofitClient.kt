package com.mmr.newsapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://newsapi.org/v2/"

    companion object Factory {
        fun create(): RetrofitClient = RetrofitClient()
    }

    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}