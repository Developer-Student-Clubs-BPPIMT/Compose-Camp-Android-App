package com.example.rockpaperscissor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rockpaperscissor.ui.theme.RockPaperScissorTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RockPaperScissorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RPS()
                }
            }
        }
    }

}

fun logic(player : String , computer : String) : Int{
    // player win -> win = 1
    // computer win -> win = 0
    // draw -> win = 2
    var win = 0;

    if(player == computer){
        win = 2
    }
    else if(player == "Rock" && computer == "Paper"){
        win = 0
    }
    else if(player == "Rock" && computer == "Scissor"){
        win = 1
    }
    else if(player == "Paper" && computer == "Rock"){
        win = 1
    }
    else if(player == "Paper" && computer == "Scissor"){
        win = 0
    }
    else if(player == "Scissor" && computer == "Paper"){
        win = 1
    }
    else if(player == "Scissor" && computer == "Rock"){
        win = 0
    }
    return win
}

fun ComputerChoice() : String{
    var list = listOf("Rock" , "Paper" , "Scissor")
    val randomIndex = Random.nextInt(list.size)
    return list[randomIndex]
}

@Composable
fun Playeraction(playera : String , actionc : String){
    Text(
        text = playera,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
        )
    Text(
        text = actionc,
        fontSize = 32.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )

}
@Composable
fun Btn(bvalue : String , onClick:()->Unit){
    Button(
        modifier = Modifier
            .height(108.dp)
            .width(108.dp)
            .padding(10.dp),
        onClick = onClick,
        shape = RoundedCornerShape((14.dp))

    ) {
        Text(
            text = bvalue
        )
    }
}

@Composable
fun RPS(){
    var image = painterResource(id = R.drawable.rock)
    var tscore by remember { mutableStateOf("0 / 0")}
    var faction by remember { mutableStateOf("Rock")}
    var aaction by remember { mutableStateOf("Paper")}
    var pscore by remember { mutableStateOf(0)}
    var ascore by remember { mutableStateOf(0)}

    Column {
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = "Score",
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = tscore,
            fontSize = 50.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.padding(85.dp)
        )
        {
           Column(
               modifier = Modifier
                   .fillMaxWidth(0.5f)
                   .wrapContentWidth(Alignment.CenterHorizontally)
           ) {
               Playeraction("You Chose" , faction)
           }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Playeraction("You Chose" , aaction)
            }

        }

        Row(
            modifier = Modifier.padding(top = 70.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    ) {
                Btn(bvalue = "Rock"){
                    faction = "Rock"
                    aaction = ComputerChoice()
                    val win = logic(faction , aaction)
                    if(win == 1) {
                        pscore++
                    }
                    else if(win == 0){
                        ascore++
                    }
                    tscore = "$pscore / $ascore"
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Btn(bvalue = "Paper") {
                    faction = "Paper"
                    aaction = ComputerChoice()
                    val win = logic(faction, aaction)
                    if (win == 1) {
                        pscore++
                    } else if (win == 0) {
                        ascore++
                    }
                    tscore = "$pscore / $ascore"
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth(0.9999f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Btn(bvalue = "Scissor"){
                    faction = "Scissor"
                    aaction = ComputerChoice()
                    val win = logic(faction , aaction)
                    if(win == 1) {
                        pscore++
                    }
                    else if(win == 0){
                        ascore++
                    }
                    tscore = "$pscore / $ascore"
                }
            }

        }

        Row (
            verticalAlignment = Alignment.Bottom ,
            modifier = Modifier.height(200.dp)
                ){
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "#JetpackCompose",
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(5.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RockPaperScissorTheme {
        RPS()
    }
}