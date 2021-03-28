package com.example.lesson_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var picture: ImageView
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        nextButton.setOnClickListener { goNext() }
    }

    private fun initViews() {
        textView = findViewById(R.id.textView)
        textView.text = getString(R.string.pushkinPoem)
        picture = findViewById(R.id.picture)
        nextButton = findViewById(R.id.nextButton)
    }

    private fun goNext() {
        val activity2 = Intent(this, Activity2::class.java)
        startActivity(activity2)
    }
}