package com.example.shevchenko_lesson_5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shevchenko_lesson_5.databinding.Activity3Binding
import com.google.android.material.snackbar.Snackbar


class Activity3 : AppCompatActivity() {
    private lateinit var binding: Activity3Binding
    private val TAKE_SNACK_TEXT: Int = 0
    private val NAME_OF_TEXT = getString(R.string.key_for_intent)
    var text: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.button3To1.setOnClickListener { goToActivity1() }
        binding.button3To5.setOnClickListener { goToActivity5() }
    }

    private fun goToActivity1() {
        val intent = Intent(this@Activity3, Activity1::class.java)
        startActivity(intent)
    }

    private fun goToActivity5() {
        val intent = Intent(this@Activity3, Activity5::class.java)
        startActivityForResult(intent, TAKE_SNACK_TEXT)
    }

    override fun onResume() {
        super.onResume()
        if (text != "") Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == TAKE_SNACK_TEXT) && (resultCode == RESULT_OK)) {
            text = data!!.getStringExtra(NAME_OF_TEXT).toString()
        }
    }
}