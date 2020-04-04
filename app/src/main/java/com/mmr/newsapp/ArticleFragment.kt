package com.mmr.newsapp

import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

class ArticleFragment : Fragment() {

    companion object {
        private val ARG_ARTICLE_URL = "article_url"
        private val ARG_ARTICLE_TITLE = "article_title"

        var articleUrl : String = ""
        var articleTitle : String = ""

        fun newInstance(title: String, url: String): ArticleFragment {
            val args = Bundle()
            args.putString(ARG_ARTICLE_URL,url)
            args.putString(ARG_ARTICLE_TITLE,title)
            val fragment = ArticleFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var articleWebView: WebView
    lateinit var progressBar : ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.article_fragment,
            container, false)
        val activity = activity as? MainActivity
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        articleWebView = rootView.findViewById(R.id.article_webview)
        progressBar = rootView.findViewById(R.id.article_loading)

        arguments?.let {
            articleUrl = it.getString(ARG_ARTICLE_URL,"")
            articleTitle = it.getString(ARG_ARTICLE_TITLE,"")
        }

        activity?.supportActionBar?.title = articleTitle

        articleWebView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                view.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                view.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
            }

        }

        articleWebView.loadUrl(articleUrl)

        return rootView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val activity = activity as? MainActivity
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}