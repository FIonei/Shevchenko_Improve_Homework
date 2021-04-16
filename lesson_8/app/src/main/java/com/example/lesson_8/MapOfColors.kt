package com.example.lesson_8

data class MapOfColors(
    private val map: Map<String, Int> = mapOf(
        "lipstick" to R.color.lipstick,
        "lipstick_two" to R.color.lipstick_two,
        "barney" to R.color.barney,
        "bluey_purple" to R.color.blue_purple,
        "lightish_blue" to R.color.lightish_blue,
        "azure" to R.color.azure,
        "turquoise_blue" to R.color.turquoise_blue,
        "teal" to R.color.teal,
        "booger" to R.color.booger,
        "sickly_yellow" to R.color.sickly_yellow,
        "sunshine_yellow" to R.color.sunshine_yellow,
        "marigold" to R.color.marigold,
        "orange_red" to R.color.orange_red,
        "warm_grey_five" to R.color.warm_grey_five,
        "blue_grey" to R.color.blue_grey,
        "white" to R.color.white
    ),
    private var color: String = "white"
) {
    fun getColor(str: String): Int = map.getValue(str)
    fun getCurrentColor(): String = color
    fun setCurrentColor(color: String) {
        this.color = color
    }
}