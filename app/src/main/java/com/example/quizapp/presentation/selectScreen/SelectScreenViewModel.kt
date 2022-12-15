package com.example.quizapp.presentation.selectScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectScreenViewModel @Inject constructor():ViewModel() {
    
    var selectedCategory= mutableStateOf("")
    var selectedDifficulty= mutableStateOf("")

    
}