package com.example.lesson_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class Display1 : AppCompatActivity() {
    private val listName: TreeSet<String> = TreeSet<String>()
    lateinit var list: TextView
    lateinit var saveButton: Button
    lateinit var showButton: Button
    lateinit var input: EditText
    lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display1)
        initViews()

        backButton.setOnClickListener { goBack() }
        saveButton.setOnClickListener { addName() }
        showButton.setOnClickListener { showNames() }
    }

    private fun goBack() {
        this.finish()
    }

    private fun initViews() {
        backButton = findViewById(R.id.backButton)
        list = findViewById(R.id.list)
        input = findViewById(R.id.editInputText)
        saveButton = findViewById(R.id.saveButton)
        showButton = findViewById(R.id.showButton)
    }

    private fun addName() {
        val text = input.text.toString()
        listName.add(text)
        Toast.makeText(this, "$text added", Toast.LENGTH_SHORT).show()
        input.setText("")
    }

    private fun checkUniq(list: TreeSet<String>, name: String): Boolean =
        list.contains(name.trim()).not()

    private fun showNames() {
        val names = listName.sorted()
        val result: String = names
            .takeIf { it.isNotEmpty() }
            ?.reduce { acc, s -> "$acc\n $s" } ?: ""
        list.text = result
    }
}