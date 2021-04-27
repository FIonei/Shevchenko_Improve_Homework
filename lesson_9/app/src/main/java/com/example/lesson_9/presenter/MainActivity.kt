package com.example.lesson_9.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson_9.R
import com.example.lesson_9.data.Links
import com.example.lesson_9.data.Main
import com.example.lesson_9.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var json: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startUpdateService()
        showTemperature(readJson(Links().apiLink()))
        binding.downloadButton.setOnClickListener { download() }
    }

    private fun showTemperature(json: String) {
        val gson = Gson()
        val weather = gson.fromJson(json, Main::class.java) //string json to gson object
        binding.current.text = applicationContext.getString(R.string.temperature_format_int, weather.main.temp!!.toInt())
        binding.feel.text = applicationContext.getString(R.string.temperature_format_int, weather.main.feels_like!!.toInt())
        binding.minimal.text = applicationContext.getString(R.string.temperature_format_int, weather.main.temp_min!!.toInt())
        binding.maximal.text = applicationContext.getString(R.string.temperature_format_int, weather.main.temp_max!!.toInt())
    }

    private fun startUpdateService() {
        //TODO: service через bind service, который получает погоду каждую минуту
    }

    private fun download() {
        binding.weatherImage.setImageDrawable(getDrawable(R.drawable.ic_launcher_background))
    }

    private fun readJson(apiLink: String): String {
        return """{"coord":{"lon":45.1749,"lat":54.1838},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02d"}],"base":"stations","main":
            |{"temp":6.75,"feels_like":2.59,"temp_min":6.75,"temp_max":6.75,"pressure":1016,"humidity":46,"sea_level":1016,"grnd_level":995},"visibility":10000,"wind":{"speed":7.54,"deg":255,"gust":10.16},"clouds":{"all":11},"dt":1619420446,"sys":
            |{"country":"RU","sunrise":1619400765,"sunset":1619454069},"timezone":10800,"id":498698,"name":"Saransk","cod":200}""".trimMargin()
    }
}