package com.jans.model.view.test.app.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jans.model.view.test.app.Config.Companion.goToScreen
import com.jans.model.view.test.app.databinding.ActivityHomeBinding

class HomeScreen : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.btn1.setOnClickListener {
            goToScreen(this,ModelView1Screen::class.java)
        }




    }







}