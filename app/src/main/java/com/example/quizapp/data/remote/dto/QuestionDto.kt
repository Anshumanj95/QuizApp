package com.example.quizapp.data.remote.dto

import com.example.quizapp.domain.model.Question

data class QuestionDto(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: List<String>,
    val question: String,
    val type: String
)
fun QuestionDto.toQuestion(): Question {
    return Question(
        correct_answer = correct_answer,
        incorrect_answers = incorrect_answers,
        question=question
    )
}