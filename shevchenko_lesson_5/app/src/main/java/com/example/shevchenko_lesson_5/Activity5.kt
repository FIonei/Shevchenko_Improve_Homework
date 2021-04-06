package com.example.shevchenko_lesson_5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shevchenko_lesson_5.databinding.Activity5Binding

class Activity5 : AppCompatActivity() {
    private val NAME_OF_TEXT = "text5"
    private val STATE_KEY_TEXT = "TextView"
    private val STATE_KEY_EDIT = "EditText"
    private lateinit var binding: Activity5Binding
    private val inputtedText: Data = Data()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity5Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (inputtedText.isValueEmpty().not()) {
            var text = ""
            for (value in inputtedText.getAllValue()) text += value!!
            binding.inputEditText.setText(text)
        }
        binding.button5To3.setOnClickListener { goToActivity3() }
        binding.saveButton.setOnClickListener { saveText() }
    }

    private fun saveText() {
        if (binding.inputEditText.text.toString() == "") return
        else {
            inputtedText.setValue(binding.inputEditText.text.toString())
            if (inputtedText.isValueEmpty().not()) {
                var allText: String = binding.outputTextView.text.toString()
                for (string in inputtedText.getAllValue()) allText += string!!
                binding.outputTextView.text = allText
            }
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putString(STATE_KEY_EDIT, binding.inputEditText.text.toString())
        savedInstanceState.putString(STATE_KEY_TEXT, binding.outputTextView.text.toString())
        super.onSaveInstanceState(savedInstanceState)
    }

    private fun goToActivity3() {
        val intent = Intent()
        if (binding.inputEditText.text != null) {
            intent.putExtra(NAME_OF_TEXT, binding.inputEditText.text.toString())
            setResult(RESULT_OK, intent)
        } else setResult(RESULT_CANCELED)
        finish()
    }
}