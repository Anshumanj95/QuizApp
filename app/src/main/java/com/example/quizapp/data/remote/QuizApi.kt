package com.example.quizapp.data.remote

import com.example.quizapp.data.remote.dto.QuestionDto
import com.example.quizapp.data.remote.dto.QuestionsDto
import retrofit2.http.GET
import retrofit2.http.Query


interface QuizApi {

    @GET("/api.php")
    suspend fun getQuestions(
        @Query("amount")no:Int,
        @Query("category")category:Int,
        @Query("difficulty")difficulty:String
    ): QuestionsDto
}