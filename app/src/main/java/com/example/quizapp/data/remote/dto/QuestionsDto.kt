package com.example.quizapp.data.remote.dto

import com.example.quizapp.domain.model.Questions

data class QuestionsDto(
    val response_code: Int,
    val results: List<QuestionDto>
)
fun QuestionsDto.toQuestions():Questions{
    return Questions(results)
}