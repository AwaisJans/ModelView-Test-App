package com.jans.model.view.test.app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
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
import com.jans.model.view.test.app.model.model1Classes.Model1BaseModelClass
import com.jans.model.view.test.app.model.model2Classes.Model2BaseModelClass

class ProductsAdapter(private val productsList: List<Any>?, private val modelToBe: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post,
            parent, false)
        return ItemsVH(view)
    }

    lateinit var mContext: Context
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mContext = holder.itemView.context
        when (holder) {
            is ItemsVH -> {
                when(modelToBe){
                    1 ->  model1CodeItemRH(holder,position)
                    2 ->  model2CodeItemRH(holder,position)
                }
            }
            else -> return
        }
    }

    private fun model1CodeItemRH(holder: ItemsVH,position: Int) {
        val post: Model1BaseModelClass.Item = (productsList?.get(position) as Model1BaseModelClass.Item?)!!
        holder.titleTextView.text = post.bezeichnung
        // webview Code
        webViewCodeItemRH(holder,post.beschreibung)
        // item Click Code
        itemClickCodeItemRH(holder,post.urlPopUp)
    }

    private fun model2CodeItemRH(holder: ItemsVH,position: Int) {
        val post: Model2BaseModelClass.Item = (productsList?.get(position) as Model2BaseModelClass.Item?)!!
        holder.titleTextView.text = post.bezeichnung
        // webview Code
        webViewCodeItemRH(holder,post.beschreibung)
        // item Click Code
        itemClickCodeItemRH(holder,post.urlDetails)
    }

    private fun itemClickCodeItemRH(holder: ItemsVH, url:String) {
        holder.itemView.setOnClickListener {
            startActivity(mContext, Intent(mContext,
                DetailScreen::class.java).
            putExtra("url", url).
            putExtra("urlToBe",modelToBe), null)
        }
    }

    private fun webViewCodeItemRH(holder: ItemsVH,htmlContent:String){
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
                if (htmlContent == "") {
                    pBar.visibility = GONE
                    webView.visibility = GONE
                } else {
                    pBar.visibility = GONE
                    webView.visibility = VISIBLE
                }
            }
        }
        webView.loadDataWithBaseURL(
            null,
            htmlContent, "text/html",
            "UTF-8", null
        )
    }

    override fun getItemCount(): Int = productsList!!.size

    class ItemsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        var webView: WebView = itemView.findViewById(R.id.webView)
        var pBar: ProgressBar = itemView.findViewById(R.id.pBar)

    }
}