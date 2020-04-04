package com.mmr.newsapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListFragment : Fragment(), ArticlesAdapter.OnArticleListener {


    lateinit var newsRecyclerView: RecyclerView
    val adapter = ArticlesAdapter(this)
    lateinit var articles: List<ArticleResponse.Article>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.news_list_fragment,
            container, false)
        val activity = activity as? MainActivity
        activity?.supportActionBar?.title = "Latest News"
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        newsRecyclerView = rootView.findViewById(R.id.news_list)
        newsRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        newsRecyclerView.adapter = adapter

        return rootView
    }

    override fun onStart() {
        super.onStart()
        val call : Call<ArticleResponse>  = RetrofitClient.create()
            .getRetrofitInstance()!!.create(NewsApiService::class.java).fetchNewsList()
        call.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                Log.d("asd", "success")
                if (response.code() == 200) {
                    articles = response.body()!!.articles
                    adapter.setArticles(articles)
                }
            }
            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onArticleClick(position: Int) {
        val transaction: FragmentTransaction =
            activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,
            ArticleFragment.newInstance(articles[position].title, articles[position].url))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}