package com.example.cloud.ui.theme

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val BackgroundColor = Color(0xFF242433)
val ComponentBackgroundColor = Color(0xFF7B7BA7)



// background
val BackgroundDark = Color(0xFF001C30 )


// components
val ComponentDark = Color(0xFF073C61)
val CommonComponent = Color(0xFF02457A)

// helper colors
val HelperColor = Color(0xFF0343A7)
val HelperColor1 = Color(0xFF0E274E)
val HelperColor2 = Color(0xFF06BAF1)


val brush =  Brush.linearGradient(
    colors = listOf(
        Color.Gray.copy(alpha = 0.6f),
        Color.Gray.copy(alpha = 0.2f),
        Color.Gray.copy(alpha = 0.6f)
    )
)



@Composable
fun animatedShimmerColor(
    shimmerColors:List<Color>,
    durationMillis:Int = 1000
): Brush {

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )


}

val colorList = listOf(
    Color(0xFF350775),
    Color(0xFF3E057C),
    Color(0xFF490692),
    Color(0xFF570AAA),
    Color(0xFF5B0CB1),
    Color(0xFF6506CC),
    Color(0xFF8F34F3),
    Color(0xFF8B44DA),
    Color(0xFF086CB1),
    Color(0xFF05578D),
    Color(0xFF06578B),
    Color(0xFF044069),
    Color(0xFF063D63),
)