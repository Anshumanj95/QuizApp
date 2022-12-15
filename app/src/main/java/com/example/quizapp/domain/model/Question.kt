package com.example.quizapp.domain.model

data class Question(
    val correct_answer: String,
    val incorrect_answers: List<String>,
    val question: String,
)