package com.example.lesson_7

import com.google.gson.annotations.SerializedName

data class BridgeInfoItem(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val bridgeName: String? = null,
    @SerializedName("name_end") val name_eng: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("description_eng") val description_eng: String? = null,
    @SerializedName("divorces") val divorces: List<Divorce>? = listOf(Divorce(null, null)),
    @SerializedName("lat") val lat: Double? = null,
    @SerializedName("lng") val lng: Double? = null,
    @SerializedName("photo_close_url") val photo_close_url: String? = null,
    @SerializedName("photo_open_url") val photo_open_url: String? = null,
    @SerializedName("public") val isPublic: Boolean? = null
)