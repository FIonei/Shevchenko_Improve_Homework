package com.example.lesson_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import java.lang.StringBuilder
import java.util.*

class Display2 : AppCompatActivity() {
    private lateinit var peopleInput: EditText
    private lateinit var showButton2: Button
    private lateinit var peopleOutput: TextView
    private lateinit var backButton2: Button
    private val base_year = -1
    private var students: MutableList<Student> = mutableListOf(Student(0, "", "", "", base_year))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display2)
        initViews()
        peopleInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val elements: List<String>
                val inputted: String = peopleInput.text.toString().trim()
                if (checkString(inputted)) {
                    elements = (inputted.split(" "))
                    if (students[0].birthdayYear == base_year) students[0] = listToStudent(0, elements)
                    else students.add(listToStudent((students.last().id) + 1, elements))
                    peopleInput.setText("")
                } else Toast.makeText(this, getString(R.string.wrong_data), Toast.LENGTH_SHORT)
                    .show()
                return@OnKeyListener true
            }
            false
        })

        showButton2.setOnClickListener { showStudents() }
        backButton2.setOnClickListener { goBack() }
    }

    private fun goBack() {
        this.finish()
    }

    private fun showStudents() {
        if (students[0].birthdayYear != base_year) {
            val sb = StringBuilder()
            for (string in students) {
                sb.append(string.studentString() + "\n")
            }
            peopleOutput.text = sb
        } else Toast.makeText(this, getString(R.string.no_pupil), Toast.LENGTH_SHORT).show()
    }

    private fun listToStudent(id: Int, elements: List<String>): Student =
        Student(
            id = id,
            name = elements[0].capitalize(Locale.ROOT),
            surname = elements[1].capitalize(Locale.ROOT),
            grade = elements[2].toUpperCase(Locale.ROOT),
            birthdayYear = elements[3].toInt()
        )

    private fun checkString(string: String): Boolean {
        val count = 4
        val list = string.split(" ").toList()
        if (list.count() != count) return false
        return list[3].isDigitsOnly()
    }

    private fun initViews() {
        peopleInput = findViewById(R.id.peopleInput)
        showButton2 = findViewById(R.id.showButton2)
        peopleOutput = findViewById(R.id.peopleOutput)
        backButton2 = findViewById(R.id.backButton2)
    }
}