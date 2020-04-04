package com.mmr.newsapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mmr.newsapp.ArticleResponse.Article
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ArticlesAdapter(val listener: OnArticleListener) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    var articleList: List<Article> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(articleList[position])
        holder.itemView.setOnClickListener{
            listener.onArticleClick(position)
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun setArticles(articles : List<Article> ){
        articleList = articles
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(article: Article) {
            val image = itemView.findViewById(R.id.article_image) as ImageView
            val title = itemView.findViewById(R.id.artcile_title) as TextView
            val date  = itemView.findViewById(R.id.article_date) as TextView
            title.text = article.title
            date.text = article.publishedAt
            Glide.with(itemView.context).load(article.urlToImage).placeholder(ColorDrawable(Color.LTGRAY)).into(image)
        }
    }

    interface OnArticleListener {
        fun onArticleClick(position: Int)
    }
}