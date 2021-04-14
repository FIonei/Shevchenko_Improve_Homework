package com.example.lesson_7

import retrofit2.Call
import retrofit2.http.GET

interface GetItemsService {
    @GET("bridges")
    fun getAllItems() : Call<MutableList<BridgeInfoItem>>
}