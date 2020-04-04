package com.mmr.newsapp

import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {
    @GET("top-headlines?country=us&apiKey=1f0bb72707ef4e17920e2fcc6b813c6d")
    fun fetchNewsList(): Call<ArticleResponse>
}
