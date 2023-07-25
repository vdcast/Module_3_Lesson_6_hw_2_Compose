package com.example.module_3_lesson_6_hw_2_compose.ui.settings.language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.module_3_lesson_6_hw_2_compose.R

@Composable
fun LanguageScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.language))
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {  }
        ) { Text(text = stringResource(id = R.string.change_language)) }
    }
}