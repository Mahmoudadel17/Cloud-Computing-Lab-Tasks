package com.example.cloud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cloud.ui.theme.CloudTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CloudTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppName()
                    Animation()
                }
            }
        }
    }
}


@Composable
fun Animation() {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LottieAnimationShow(
            animationResId = R.raw.cloud,
            size = 420,
            padding = 0,
            paddingBottom = 0
        )

    }
}


@Composable
fun AppName() {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color(0xFF086CB1))) {
                append("C")
            }
            withStyle(style = SpanStyle(color = Color(0xFF05578D))) {
                append("l")
            }
            withStyle(style = SpanStyle(color = Color(0xFF06578B))) {
                append("o")
            }
            withStyle(style = SpanStyle(color = Color(0xFF044069))) {
                append("u")
            }
            withStyle(style = SpanStyle(color = Color(0xFF063D63))) {
                append("d")
            }
            append("\n\n\n")
            withStyle(style = SpanStyle(color = Color(0xFF350775))) {
                append("C")
            }
            withStyle(style = SpanStyle(color = Color(0xFF3E057C))) {
                append("o")
            }
            withStyle(style = SpanStyle(color = Color(0xFF490692))) {
                append("m")
            }
            withStyle(style = SpanStyle(color = Color(0xFF570AAA))) {
                append("p")
            }
            withStyle(style = SpanStyle(color = Color(0xFF5B0CB1))) {
                append("u")
            }
            withStyle(style = SpanStyle(color = Color(0xFF6506CC))) {
                append("t")
            }
            withStyle(style = SpanStyle(color = Color(0xFF8F34F3))) {
                append("i")
            }
            withStyle(style = SpanStyle(color = Color(0xFF8B44DA))) {
                append("n")
            }
            withStyle(style = SpanStyle(color = Color(0xFFAC6DF0))) {
                append("g")
            }
        },
        fontSize = 70.sp,
        modifier = Modifier.padding(5.dp)
    )
}