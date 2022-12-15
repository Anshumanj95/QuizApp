package com.example.quizapp.presentation.selectScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapp.common.Constants.categoryMap
import com.example.quizapp.presentation.selectScreen.SelectScreenViewModel

@Composable
fun SelectScreen(
    navController: NavController,
    viewModel: SelectScreenViewModel= hiltViewModel()
) {
    Box(modifier = Modifier.padding(20.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .align(Center)) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text="Category",
                style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(10.dp))
            SelectCategory(categoryList = categoryMap.keys, onSelectedCategory = {
                viewModel.selectedCategory.value=it
            })
            Spacer(modifier = Modifier.height(10.dp))
            Text(text="Difficulty",
                style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(10.dp))
            SelectDifficulty(onSelectedDifficulty = {
                viewModel.selectedDifficulty.value=it
            })
            Spacer(modifier = Modifier.height(15.dp))
            Button(onClick = {
                val category=viewModel.selectedCategory.value
                val difficulty=viewModel.selectedDifficulty.value
                if (category!="" && difficulty!=""){
                    navController.navigate("quiz_screen/${category}/${difficulty}")
                }
            }, modifier = Modifier.align(CenterHorizontally),
            shape = RoundedCornerShape(10.dp)) {
                Text(text = "Generate Quiz", color = MaterialTheme.colors.onPrimary)
            }
        }

    }


}