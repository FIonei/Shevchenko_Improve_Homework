package com.example.lesson_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val button: Button = findViewById(R.id.backButton)
        button.setOnClickListener { this.finish() }
    }
}