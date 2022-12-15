package com.example.quizapp.di

import com.example.quizapp.common.Constants.BASE_URL
import com.example.quizapp.data.remote.QuizApi
import com.example.quizapp.data.repository.QuizRepositoryImpl
import com.example.quizapp.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApi():QuizApi{
        val interceptor:HttpLoggingInterceptor =HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client:OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuizRepository(api:QuizApi):QuizRepository{
        return QuizRepositoryImpl(api)
    }



}