package com.jans.model.view.test.app.activities

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jans.model.view.test.app.Config.Companion.LoadingIndicatorView
import com.jans.model.view.test.app.Config.Companion.readJsonFileWithGson
import com.jans.model.view.test.app.R
import com.jans.model.view.test.app.adapter.ProductsAdapter
import com.jans.model.view.test.app.databinding.ActivityModelView1ScreenBinding
import com.jans.model.view.test.app.model.model1Classes.Model1BaseModelClass
import com.jans.model.view.test.app.model.model2Classes.Model2BaseModelClass


class RVScreen : AppCompatActivity() {

    private lateinit var binding: ActivityModelView1ScreenBinding

    private lateinit var productsAdapter: ProductsAdapter

    lateinit var loadingIndicator: LoadingIndicatorView
    var title = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModelView1ScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*
           Code for Setting up Indicator
        */
        setupIndicator()

        val selectModelToBe = intent.getIntExtra("ModelToBe", 0)
        // Selecting Model Decision
        when (selectModelToBe) {
            1 -> model1RVCode()
            2 -> model2RVCode()
        }

        binding.toolbar.title = title

    }


    private fun model1RVCode() {
        title = "Model1 RV Screen"
        Handler().postDelayed({
            loadingIndicator.stopAnimating();
            val recyclerView = binding.recyclerView
            /*
                Model 1 Code to Populate List
             */
            val jsonString = readJsonFileWithGson(this, R.raw.model_1)
            val api = Gson().fromJson(jsonString, Model1BaseModelClass::class.java).items
            Log.d("new123", "${api.size}")
            /*
                Model 1 Code for Recycler View
            */
            productsAdapter = ProductsAdapter(api.toMutableList(), 1)
            recyclerView.visibility = VISIBLE
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = productsAdapter
        }, 2000)
    }

    private fun model2RVCode() {
        title = "Model2 RV Screen"
        Handler().postDelayed({
            loadingIndicator.stopAnimating();
            val recyclerView = binding.recyclerView
            /*
                Model 2 Code to Populate List
             */
            val jsonString = readJsonFileWithGson(this, R.raw.model_2)
            val api = Gson().fromJson(jsonString, Model2BaseModelClass::class.java).items
            Log.d("new123", "${api.size}")
            /*
                Model 2 Code for Recycler View
            */
            productsAdapter = ProductsAdapter(api.toMutableList(),2)
            recyclerView.visibility = VISIBLE
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = productsAdapter
        }, 2000)

    }

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
        mainLayout.addView(loadingIndicator)
        setContentView(mainLayout)
        loadingIndicator.startAnimating()

    }

}