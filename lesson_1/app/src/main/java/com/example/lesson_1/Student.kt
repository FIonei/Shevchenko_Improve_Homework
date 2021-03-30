package com.example.lesson_1

data class Student(
    val id: Int,
    val name: String,
    val surname: String,
    val grade: String,
    val birthdayYear: Int
) {

    fun studentString(): String {
        return ("$name $surname $grade $birthdayYear")
    }
}
