package com.example.shevchenko_lesson_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shevchenko_lesson_5.databinding.Activity1Binding

class Activity1 : AppCompatActivity() {
    private lateinit var binding: Activity1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.button1To4.setOnClickListener { goToActivity4() }
        binding.button1To2.setOnClickListener { goToActivity2() }
    }

    private fun goToActivity2() {
        val intent = Intent(this@Activity1, Activity2::class.java)
        startActivity(intent)
    }

    private fun goToActivity4() {
        val intent = Intent(this@Activity1, Activity4::class.java)
        startActivity(intent)
    }
}