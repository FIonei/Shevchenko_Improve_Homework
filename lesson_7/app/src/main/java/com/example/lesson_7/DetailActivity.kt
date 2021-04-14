package com.example.lesson_7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.lesson_7.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val imageEx: String = "image"
    private val nameEx: String = "bridgeName"
    private val timeEx: String = "bridgeTime"
    private val commentEx: String = "bridgeCommentary"

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.title = null
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { finish() }

        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            val extras = intent.extras!!
            binding.bridgeImage.setImageDrawable(ContextCompat.getDrawable(this, extras.getInt(imageEx)))
            binding.bridgeName.text = extras.getString(nameEx)
            binding.bridgeTime.text = extras.getString(timeEx)
            binding.bridgeCommentary.text = extras.getString(commentEx)
        }
    }
}