package com.example.module_3_lesson_6_hw_2_compose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.module_3_lesson_6_hw_2_compose.R
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Module_3_Lesson_6_hw_2_ComposeTheme

@Composable
fun SwitchRow(text: String, isChecked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_medium)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text
        )
        Switch(
            checked = isChecked,
            onCheckedChange = { newValue ->
                onChange(newValue)
            }
        )
    }
}
@Composable
fun StatisticsRow(textTitle: String, textValue: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_medium)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text( text = textTitle )
        Text(text = textValue)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsOutlinedTextFiled(value: String, onValueChange: (String) -> Unit, labelId: Int ) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = labelId)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
    )
}

@Preview
@Composable
fun PreviewTwo() {
    Module_3_Lesson_6_hw_2_ComposeTheme {

    }
}