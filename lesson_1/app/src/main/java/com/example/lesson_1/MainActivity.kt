package com.example.lesson_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        button1.setOnClickListener { openDisplay1() }
        button2.setOnClickListener { openDisplay2() }
    }

    private fun openDisplay1() {
        val startFirst = Intent(this, Display1::class.java)
        startActivity(startFirst)
    }

    private fun openDisplay2() {
        val startSecond = Intent(this, Display2::class.java)
        startActivity(startSecond)
    }
}