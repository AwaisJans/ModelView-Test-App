package com.jans.model.view.test.app.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.jans.model.view.test.app.R
import com.jans.model.view.test.app.activities.DetailScreen
import com.jans.model.view.test.app.model.model1BaseModelClasses.Item

class ProductsAdapter(private val productsList: List<Item>?) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context

        val post = productsList?.get(position)!!

        holder.titleTextView.text = post.bezeichnung

        val webView = holder.webView
        val pBar = holder.pBar


        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pBar.visibility = VISIBLE
                webView.visibility = GONE

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                if(post.beschreibung == ""){
                    pBar.visibility = GONE
                    webView.visibility = GONE
                }
                else{
                    pBar.visibility = GONE
                    webView.visibility = VISIBLE
                }

            }
        }

        Log.d("webView123",
            "${post.beschreibung} ${post.bezeichnung}")

        webView.loadDataWithBaseURL(null,
            post.beschreibung, "text/html",
            "UTF-8", null)


        holder.itemView.setOnClickListener{
            startActivity(context,Intent(context, DetailScreen::class.java).
            putExtra("url", post.urlPopUp),null)
            }
        }


    override fun getItemCount(): Int = productsList!!.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        var webView: WebView = itemView.findViewById(R.id.webView)
        var pBar: ProgressBar = itemView.findViewById(R.id.pBar)

    }
}