package com.example.shevchenko_lesson_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shevchenko_lesson_5.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {
    private lateinit var binding: Activity2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.button2To3.setOnClickListener{ goToActivity3() }
    }

    private fun goToActivity3() {
        val intent = Intent(this@Activity2, Activity3::class.java)
        startActivity(intent)
    }
}