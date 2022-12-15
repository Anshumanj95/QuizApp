package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizapp.presentation.quizScreen.components.QuizScreen
import com.example.quizapp.presentation.quizScreen.components.ResultScreen
import com.example.quizapp.presentation.selectScreen.component.SelectScreen
import com.example.quizapp.ui.theme.QuizAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.surface
                ) {
                    val navController= rememberNavController()
                    NavHost(navController = navController,
                        startDestination =  "select_screen"
                    ){
                        composable(
                            route = "select_screen"){
                            SelectScreen(navController = navController)
                        }
                        composable(route = "quiz_screen/{category}/{difficulty}",
                        arguments = listOf(
                            navArgument("category"){type= NavType.StringType},
                            navArgument("difficulty"){type= NavType.StringType}
                        )){
                            val category= remember {
                                it.arguments?.getString("category")
                            }
                            val difficulty= remember {
                                it.arguments?.getString("difficulty")
                            }
                            QuizScreen(category = category!!, difficulty = difficulty!!,navController)
                        }
                        composable(route = "point_screen/{points}",
                        arguments = listOf(navArgument("points"){
                            type= NavType.IntType
                        })){
                            val points= remember {
                                it.arguments?.getInt("points")
                            }
                            ResultScreen(points = points!!)
                        }
                    }
                }
            }
        }
    }
}
