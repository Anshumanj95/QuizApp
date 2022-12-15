package com.example.quizapp.data.repository

import android.util.Log
import com.example.quizapp.common.Resource
import com.example.quizapp.data.remote.QuizApi
import com.example.quizapp.data.remote.dto.QuestionsDto
import com.example.quizapp.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val api:QuizApi):QuizRepository
{
    override suspend fun getQuestions(category:Int,difficulty:String): Resource<QuestionsDto> {
        val response=try{
            api.getQuestions(10,category,difficulty)
        }catch (e:Exception){
            return Resource.Error("${e.message}")
        }
        return Resource.Success(response)
    }

}