package com.jans.model.view.test.app.activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.jans.model.view.test.app.Config.Companion.LoadingIndicatorView
import com.jans.model.view.test.app.R
import com.jans.model.view.test.app.databinding.ActivityDetailScreenBinding
import com.jans.model.view.test.app.model.model1Classes.Model1DetailModelClass
import com.jans.model.view.test.app.model.model2Classes.Model2DetailModelClass


class DetailScreen : AppCompatActivity() {

    private lateinit var binding: ActivityDetailScreenBinding
    private lateinit var loadingIndicator: LoadingIndicatorView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setting indicator
        setupIndicator()

        // setting required values
        val url = intent.getStringExtra("url")!!
        val urlToBe = intent.getIntExtra("urlToBe", 1)
        binding.toolbar.title = "Loading..."

        // make decision which url will be select according to model
        when (urlToBe) {
            1 -> setupApi(url, urlToBe)
            2 -> setupApi(url, urlToBe)
        }
    }

    private fun model2Code(user: Model2DetailModelClass) {
        val singleItem = user.singleItem
        val webView = binding.webView
        val htmlContent = singleItem.beschreibung
        binding.toolbar.title = singleItem.bezeichnung
        binding.descTextView.text = singleItem.ort
        Glide.with(this).load(R.mipmap.pic1).into(binding.img1)
        if (htmlContent == "") {
            webView.visibility = GONE
            binding.descTextView.visibility = VISIBLE
        } else {
            webView.visibility = VISIBLE
            binding.descTextView.visibility = GONE
            webView.loadDataWithBaseURL(
                null,
                singleItem.beschreibung, "text/html",
                "UTF-8", null
            )
        }


    }

    private fun model1Code(user: Model1DetailModelClass) {
        val singleItem = user.singleItem
        binding.toolbar.title = singleItem.bezeichnung
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.pic1)
            .error(R.mipmap.pic2)
        try {
            Glide.with(this).load(singleItem.bild.url).apply(options).into(binding.img1)
            binding.descTextView.text = "Description: ${singleItem.beschreibung}"
        } catch (e: NullPointerException) {
            Log.d("resp123", "NullPointerException")
            Glide.with(this).load(R.mipmap.pic2).into(binding.img1)
        }
    }


    // getting json from api
    private fun setupApi(url: String, urlToBe: Int) {
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { jsonString ->
//                 setting up model according to url
                when (urlToBe) {
                    1 -> model1Code(Gson().fromJson(jsonString.toString(),Model1DetailModelClass::class.java))
                    2 -> model2Code(Gson().fromJson(jsonString.toString(),Model2DetailModelClass::class.java))
                }
                loadingIndicator.visibility = GONE
                Log.d("resp123", "Response: $jsonString")
            }, { error -> // Handle errors
                Log.e("resp123", "Error: ${error.message}")
            })
        queue.add(jsonObjectRequest)
    }

    // setting indicator
    private fun setupIndicator() {
        val mainLayout = binding.main
        mainLayout.setBackgroundColor(Color.TRANSPARENT)
        loadingIndicator = LoadingIndicatorView(this, 70);
        loadingIndicator.alpha = 1.0f;
        mainLayout.id = View.generateViewId()
        loadingIndicator.id = View.generateViewId()
        val loadingIndicatorLayoutParams = RelativeLayout.LayoutParams(
            200, 200
        )
        loadingIndicatorLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        loadingIndicator.layoutParams = loadingIndicatorLayoutParams
        mainLayout.addView(loadingIndicator);
        setContentView(mainLayout)
        loadingIndicator.startAnimating()
    }


}