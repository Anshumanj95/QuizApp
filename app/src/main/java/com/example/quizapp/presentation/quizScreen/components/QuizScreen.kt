package com.example.quizapp.presentation.quizScreen.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapp.common.Resource
import com.example.quizapp.data.remote.dto.QuestionsDto
import com.example.quizapp.data.remote.dto.toQuestion
import com.example.quizapp.domain.model.Question
import com.example.quizapp.presentation.quizScreen.QuizScreenViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun QuizScreen(
    category: String,
    difficulty:String,
    navController: NavController,
    viewModel: QuizScreenViewModel = hiltViewModel()
) {
    val questionList= mutableStateOf<List<Question>>(listOf())
    val isLoading = mutableStateOf(false)

    val isError = mutableStateOf("")

    val result= produceState<Resource<QuestionsDto>>(initialValue = Resource.Loading()){
        value=viewModel.loadQuestions(category,difficulty)
    }.value

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        when (result) {
            is Resource.Success -> {
                if (result.data!!.response_code == 0) {
                    questionList.value = result.data.results.map { it.toQuestion() }
                    isLoading.value = false

                } else {
                    isError.value="Something went wrong"
                }
            }
            is Resource.Error -> {
                isError.value="Something went wrong"
                isLoading.value = false
            }
            is Resource.Loading -> {
                isLoading.value=true
                CircularProgressIndicator(modifier = Modifier.align(Center))
            }
        }
    }
    val state= rememberLazyListState()
    LazyColumn(state = state,modifier = Modifier.padding(10.dp)){
        itemsIndexed(questionList.value){index, item ->
            QuestionItem(question = item, index = index+1)
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), contentAlignment = BottomEnd){
        if (state.isScrolledToTheEnd()){
            ExtendedFloatingActionButton(
                { Icon(Icons.Filled.ArrowForward, contentDescription = "next") },
                onClick = { navController.navigate("point_screen/${viewModel.points.value}") },
            )
        }
    }
}
fun LazyListState.isScrolledToTheEnd()=layoutInfo.visibleItemsInfo.lastOrNull()?.index==layoutInfo.totalItemsCount-1


