package com.example.quizapp.presentation.selectScreen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectDifficulty(
    onSelectedDifficulty:(String)->Unit
){
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedDifficulty by remember {
        mutableStateOf("")
    }
    val difficultyList= listOf("Easy","Medium","Hard")
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
        expanded = !expanded
    }) {
        OutlinedTextField(
            readOnly = true,
            value = selectedDifficulty,
            onValueChange = {},
            label = { Text(text = "Select Difficulty") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            difficultyList.forEach {
                DropdownMenuItem(onClick = {
                    selectedDifficulty= it
                    onSelectedDifficulty(it)
                    expanded = false
                }) {
                    Text(text = it)
                }
            }
        }
    }
}