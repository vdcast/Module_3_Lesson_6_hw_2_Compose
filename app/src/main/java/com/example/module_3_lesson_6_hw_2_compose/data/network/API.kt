package com.example.module_3_lesson_6_hw_2_compose.data.network

import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("fact")
    suspend fun fetchSomeData(): Response<ResponseMain>
}