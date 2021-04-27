package com.example.lesson_9.data

import com.google.gson.annotations.SerializedName
data class Temperature(
    @SerializedName("temp") val temp: Double?,
    @SerializedName("feels_like") val feels_like: Double?,
    @SerializedName("temp_min") val temp_min: Double?,
    @SerializedName("temp_max") val temp_max: Double?
)