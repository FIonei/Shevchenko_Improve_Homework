package com.example.lesson_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.button1.setOnClickListener { openConstraintActivity() }
        binding.button2.setOnClickListener { openNotConstraintActivity() }
    }

    private fun openConstraintActivity() {
        val startFirst = Intent(this, ConstraintActivity::class.java)
        startActivity(startFirst)
    }

    private fun openNotConstraintActivity() {
        val startSecond = Intent(this, NotConstraintActivity::class.java)
        startActivity(startSecond)
    }
}