package com.example.lesson_9.data

import android.media.Image

class Links {
    private val picture = "https://wallpapershome.ru/images/pages/pic_h/11474.jpg"
    private val zip =
        "https://drive.google.com/drive/folders/1MoWhaGck_wsDOs_4hWaeYCqxx9JlwSMs?usp=sharing"
    private val apiLink =
        "http://api.openweathermap.org/data/2.5/weather?q=saransk&units=metric&appid=a924f0f5b30839d1ecb95f0a6416a0c2"

    fun picture(): String = picture
    fun zip(): String = zip
    fun apiLink(): String = apiLink
}