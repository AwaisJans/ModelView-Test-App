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
import com.jans.model.view.test.app.adapter.ProductsAdapter
import com.jans.model.view.test.app.databinding.ActivityModelView1ScreenBinding
import com.jans.model.view.test.app.model.model1BaseModelClasses.Item
import com.jans.model.view.test.app.model.model1BaseModelClasses.ModelClass


class ModelView1Screen : AppCompatActivity() {

    private lateinit var binding: ActivityModelView1ScreenBinding

    private lateinit var productsAdapter: ProductsAdapter

    lateinit var loadingIndicator: LoadingIndicatorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModelView1ScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupIndicator()


    }

    private fun setupIndicator() {
        val mainLayout =  binding.main
        mainLayout.setBackgroundColor(Color.TRANSPARENT)
        loadingIndicator =  LoadingIndicatorView(this, 70);
        loadingIndicator.alpha = 1.0f;
        mainLayout.id = View.generateViewId()
        loadingIndicator.id = View.generateViewId()
        val loadingIndicatorLayoutParams = RelativeLayout.LayoutParams(
            200, 200
        )
        loadingIndicatorLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        loadingIndicator.layoutParams = loadingIndicatorLayoutParams
        mainLayout.addView(loadingIndicator);
        setContentView(mainLayout);
        setupRV()
    }


    private fun setupRV(){
        loadingIndicator.startAnimating()

        Handler().postDelayed({
            loadingIndicator.stopAnimating();
            val recyclerView = binding.recyclerView

            productsAdapter = ProductsAdapter(listRV())

            recyclerView.visibility = VISIBLE
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = productsAdapter

        },2000)
    }


    private fun listRV():MutableList<Item> {
        val jsonString = readJsonFileWithGson(this, com.jans.model.view.test.app.R.raw.model_1)
        val api = Gson().fromJson(jsonString, ModelClass::class.java).items

        Log.d("new123", "${api.size}")

        return api.toMutableList()
    }





}