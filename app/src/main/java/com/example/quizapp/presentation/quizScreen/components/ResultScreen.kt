package com.example.quizapp.presentation.quizScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun ResultScreen(
    points:Int
) {
    Box(modifier = Modifier.fillMaxSize().padding(10.dp)) {
            Card(modifier = Modifier.fillMaxWidth().height(150.dp)
                .padding(15.dp).align(Alignment.Center),
            shape = RoundedCornerShape(20.dp), elevation = 10.dp){
                Column(modifier = Modifier.padding(10.dp).align(Alignment.Center)) {
                    if (points<5){
                        Text(text = "Learning is never done without errors", modifier = Modifier.padding(10.dp).align(CenterHorizontally) , style = MaterialTheme.typography.body1)
                        Text(text = "Your Score is: $points ", modifier = Modifier.padding(10.dp).align(CenterHorizontally), style = MaterialTheme.typography.body1)
                    }
                    else if(points<8){
                        Text(text = "It's hard to beat a person who never gives up", textAlign = TextAlign.Center, style = MaterialTheme.typography.body1)
                        Text(text = "Your Score is: $points ", modifier = Modifier.padding(10.dp).align(CenterHorizontally), style = MaterialTheme.typography.body1)
                    }
                    else{
                        Text(text = "Never doubt your worth", modifier = Modifier.padding(10.dp).align(CenterHorizontally), style = MaterialTheme.typography.body1)
                        Text(text = "Your Score is: $points ", modifier = Modifier.padding(10.dp).align(CenterHorizontally), style = MaterialTheme.typography.body1)
                    }
                }

            }
        }
}
