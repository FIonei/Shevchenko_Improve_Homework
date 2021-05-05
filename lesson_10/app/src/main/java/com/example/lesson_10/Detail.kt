package com.example.lesson_10

object Detail {
    private val BASE_URL = "http://gdemost.handh.ru:1235/"
    val retrofitService: GetItemService
        get() = RetrofitClientInstance.getClient(BASE_URL).create(GetItemService::class.java)
}