package com.example.shevchenko_lesson_5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shevchenko_lesson_5.databinding.Activity4Binding
import java.text.SimpleDateFormat
import java.util.*

class Activity4 : AppCompatActivity() {
    private lateinit var binding: Activity4Binding
    private val pattern = "dd.MM.yyyy HH:mm:ss"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity4Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.button4To4.setOnClickListener{ onNewIntent(this.intent) }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val pattern = SimpleDateFormat(pattern, Locale.ENGLISH)
        binding.textView.text = pattern.format(Date())
    }

    override fun onStart() {
        val pattern = SimpleDateFormat(pattern, Locale.ENGLISH)
        binding.textView.text = pattern.format(Date())
        super.onStart()
    }
}