package com.example.module_3_lesson_6_hw_2_compose.presenter

import androidx.compose.runtime.Composable

interface MainActivityContract {
    interface MainView {
        fun setComposable(composable: @Composable () -> Unit)
        fun updateTopMenu()
    }

//    interface


}