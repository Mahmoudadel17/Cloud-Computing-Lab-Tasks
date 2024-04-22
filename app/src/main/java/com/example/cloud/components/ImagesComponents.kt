package com.example.cloud.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cloud.ui.theme.HelperColor1
import com.example.cloud.ui.theme.HelperColor2
import com.example.cloud.ui.theme.animatedShimmerColor

@Composable
fun ViewImage(
    image:String,
    modifier: Modifier = Modifier,
    contentDescription:String = "",
) {
    val brush = animatedShimmerColor(
        shimmerColors = listOf(
            HelperColor1.copy(alpha = 0.6f),
            HelperColor2.copy(alpha = 0.2f),
            HelperColor1.copy(alpha = 0.6f),
        ))

    AsyncImage(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(brush = brush)
            .fillMaxWidth()
            .height(220.dp)
            .padding(vertical = 1.dp)
            .shadow(
                elevation = 16.dp,
                spotColor = Color(0xFF000718),
                shape = RoundedCornerShape(16.dp)
            )
        ,
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
    )

}
