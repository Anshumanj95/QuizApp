package com.example.quizapp.presentation.quizScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizapp.domain.model.Question
import com.example.quizapp.presentation.quizScreen.QuizScreenViewModel

@Composable
fun QuestionItem(
    question: Question,
    index:Int,
    viewModel: QuizScreenViewModel= hiltViewModel()
) {
    val optionsList: ArrayList<String> = ArrayList()
    optionsList.add(question.correct_answer)
    optionsList.addAll(question.incorrect_answers)
    var isDone by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "${index}. ${question.question}",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        var selectedOne by rememberSaveable {
            mutableStateOf("")
        }
        val (selectedOption, onOptionSelected) = remember {
            mutableStateOf("")
        }
        Column {
            optionsList.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOne),
                            onClick = { onOptionSelected(text) }
                        ),
                    verticalAlignment = CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOne), modifier = Modifier.padding(start = 8.dp),
                        onClick = {
                            if (!isDone) {
                                onOptionSelected(text)
                                selectedOne = text
                                isDone = true
                                if (viewModel.check(selectedOne, question.correct_answer)) {
                                    viewModel.points.value++
                                }
                            }
                        }
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

    }
}



