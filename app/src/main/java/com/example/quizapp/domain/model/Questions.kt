package com.example.quizapp.domain.model

import com.example.quizapp.data.remote.dto.QuestionDto

data class Questions(
    val question: List<QuestionDto>
)