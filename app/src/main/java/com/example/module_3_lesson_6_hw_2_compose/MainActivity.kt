package com.example.module_3_lesson_6_hw_2_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.module_3_lesson_6_hw_2_compose.ui.MyApp
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Module_3_Lesson_6_hw_2_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Module_3_Lesson_6_hw_2_ComposeTheme {
                MyApp()
            }
        }
    }
}