package com.example.quizapp.presentation.selectScreen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizapp.presentation.selectScreen.SelectScreenViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectCategory(
    categoryList:MutableSet<String>,
    onSelectedCategory: (String)->Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedCategory by remember {
        mutableStateOf("")
    }
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
        expanded = !expanded
    }) {
        OutlinedTextField(
            readOnly = true,
            value = selectedCategory,
            onValueChange = {},
            label = { Text(text = "Select Category") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            categoryList.forEach {
                DropdownMenuItem(onClick = {
                    selectedCategory = it
                    onSelectedCategory(it)
                    expanded = false
                }) {
                    Text(text = it)
                }
            }
        }
    }
}