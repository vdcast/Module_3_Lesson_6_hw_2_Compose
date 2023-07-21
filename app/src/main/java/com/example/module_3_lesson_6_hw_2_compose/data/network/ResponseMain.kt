package com.example.module_3_lesson_6_hw_2_compose.data.network

import com.google.gson.annotations.SerializedName

data class ResponseMain (
    @SerializedName("fact") val fact : String,
    @SerializedName("length") val length : Int
)