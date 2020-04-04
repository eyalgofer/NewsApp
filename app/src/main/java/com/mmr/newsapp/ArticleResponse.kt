package com.mmr.newsapp

data class ArticleResponse(val articles: List<Article>){
    data class Article(val title: String, val url: String, val urlToImage: String, val publishedAt: String)
}

