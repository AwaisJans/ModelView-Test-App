package com.jans.model.view.test.app.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
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
import com.jans.model.view.test.app.model.model1ChildClasses.ChildBaseClassesModel1


class DetailScreen : AppCompatActivity() {

    private lateinit var binding: ActivityDetailScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("url")!!
        binding.toolbar.title = "Loading..."

        Log.d("resp123", url)


        setupIndicator(url)





    }

    private fun setupIndicator(url:String) {
        val mainLayout =  binding.main
        mainLayout.setBackgroundColor(Color.TRANSPARENT)
        val loadingIndicator = LoadingIndicatorView(this, 70);
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

        setupApi(url,loadingIndicator)
    }

    @SuppressLint("SetTextI18n")
    private fun settingUpApiData(user: ChildBaseClassesModel1){
        val singleItem = user.singleItem!!
        binding.toolbar.title = singleItem.bezeichnung
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.pic1)
            .error(R.mipmap.pic2)


        try{
            Glide.with(this).load(singleItem.bild!!.url).
            apply(options).into(binding.img1)

            binding.descTextView.text = "Description: ${singleItem.beschreibung}"




        }
        catch (e:NullPointerException){
            Log.d("resp123", "NullPointerException")
            Glide.with(this).load(R.mipmap.pic2).into(binding.img1)
        }






    }




    private fun setupApi(url:String,loadingIndicatorView:LoadingIndicatorView){
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val gson = Gson()
                val user: ChildBaseClassesModel1 = gson.fromJson(response.toString(),
                    ChildBaseClassesModel1::class.java)
                settingUpApiData(user)
                loadingIndicatorView.visibility = GONE
                Log.d("resp123", "Name: $response")
            }, { error -> // Handle errors
                Log.e("resp123", "Error: ${error.message}")
            })
        queue.add(jsonObjectRequest)
    }




}