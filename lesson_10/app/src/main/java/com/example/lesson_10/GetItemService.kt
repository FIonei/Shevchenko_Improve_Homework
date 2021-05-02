package com.example.lesson_10

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface GetItemService {
    @GET("bridges/{id}")
    @Streaming
    fun getItem(@Path("id") id: Long): Call<BridgeInfoItem>
}