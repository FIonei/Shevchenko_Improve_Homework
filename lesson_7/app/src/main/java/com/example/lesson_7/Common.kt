package com.example.lesson_7

object Common {
    private val BASE_URL = "http://gdemost.handh.ru:1235/"
    val retrofitService: GetItemsService
        get() = RetrofitClientInstance.getClient(BASE_URL).create(GetItemsService::class.java)
}