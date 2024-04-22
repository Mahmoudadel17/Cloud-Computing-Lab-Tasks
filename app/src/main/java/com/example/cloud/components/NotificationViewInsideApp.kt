package com.example.cloud.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.cloud.R
import com.example.cloud.ui.theme.ComponentBackgroundColor
import com.example.cloud.ui.theme.colorList


@Composable
fun NotificationView(
    title: String,
    message: String,
    dateTime: String
) {
    Row (
        modifier = Modifier.padding(8.dp).fillMaxSize().clip(RoundedCornerShape(16.dp)).background(ComponentBackgroundColor).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Image(
            painter = painterResource(id = R.drawable.cloud),
            modifier = Modifier.clip(CircleShape).size(50.dp),
            contentDescription = "" ,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = buildAnnotatedString {
                var i = 0
                for (c in title){
                    withStyle(style = SpanStyle(color = colorList[i % colorList.size])) {
                        append(c)
                    }
                    i++
                } },

            )
            Text(text = message, color = Color(0xFFC9CBCC))

            dateTime?.let {
                Text(
                    text = dateTime,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            }

        }

    }

}