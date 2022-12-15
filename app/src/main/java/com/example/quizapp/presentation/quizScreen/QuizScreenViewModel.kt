package com.example.quizapp.presentation.quizScreen

import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.common.Constants.categoryMap
import com.example.quizapp.common.Resource
import com.example.quizapp.data.remote.dto.QuestionsDto
import com.example.quizapp.data.remote.dto.toQuestion
import com.example.quizapp.data.remote.dto.toQuestions
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizScreenViewModel @Inject constructor(
    private val repository:QuizRepository,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    val category=savedStateHandle.get<String>("category")
    private val difficulty=savedStateHandle.get<String>("difficulty")
    var points= mutableStateOf(0)
    init {
        viewModelScope.launch {
            loadQuestions(category!!, difficulty!!)
        }

    }

    suspend fun loadQuestions(category:String,difficulty:String):Resource<QuestionsDto>{
        val categoryValue= categoryMap[category]!!
        val result=repository.getQuestions(categoryValue,difficulty.lowercase())
        return result
    }

    fun check(picked:String,correct:String):Boolean=picked==correct




}
