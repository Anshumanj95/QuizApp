package com.example.quizapp.domain.repository

import com.example.quizapp.common.Resource
import com.example.quizapp.data.remote.dto.QuestionsDto

interface QuizRepository {

    suspend fun getQuestions(category:Int,difficulty:String): Resource<QuestionsDto>

}