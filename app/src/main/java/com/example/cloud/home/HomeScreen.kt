package com.example.cloud.home

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cloud.R
import com.example.cloud.components.ButtonClickOn
import com.example.cloud.components.LottieAnimationShow
import com.example.cloud.navigation.Screens
import com.example.cloud.ui.theme.brush


@Composable
fun HomeScreen(navController: NavHostController) {
    NotificationIcon(navController)

    Column {
        AppName()
        Animation(navController)
    }
}
@Composable
fun Animation(navController: NavHostController) {
    val vm:HomeScreenViewModel = viewModel();

    val context = LocalContext.current
    val bitmap =  remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver,it)

            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver,it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let {  btm ->
                vm.onSelectImage(btm,it)
            }
        }
    }





    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (vm.state.value != null){
            Column( modifier = Modifier.padding(12.dp)) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                        tint = Color(0xFF8B44DA),
                        modifier = Modifier.size(40.dp).clickable {
                            // delete Image
                            vm.onSelectImage(null,null)
                        }.background(brush, shape = CircleShape),
                    )
                }
                Image(
                    bitmap = vm.state.value!!.asImageBitmap(),
                    contentDescription ="",
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                        .height(260.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(brush = brush)
                        .shadow(
                            elevation = 24.dp,
                            spotColor = Color(0xFF000718),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable {
                            launcher.launch("image/*")
                        },
                    contentScale = ContentScale.Crop,
                )
            }


        }else{
            LottieAnimationShow(
                animationResId = R.raw.cloud,
                size = 420,
                padding = 0,
                paddingBottom = 0
            ){
                // on animation click got to gallery to select image.
                launcher.launch("image/*")
            }
        }
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            ButtonClickOn(buttonText = "Upload Image", paddingValue = 10) {
                if (vm.state.value == null){
                    Toast.makeText(context,"Please select image you need to upload it!!",Toast.LENGTH_LONG).show()

                }else{
                    vm.onUploadImageClick(context)
                }

            }
            ButtonClickOn(buttonText = "Show All Images", paddingValue = 10) {
                navController.navigate(Screens.Images.route)
            }
        }
    }
}





@Composable
fun NotificationIcon(navController: NavHostController) {
    Row {
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {
            // go to screen contain list of notifications.
            navController.navigate(Screens.Notifications.route)
        }) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "",
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF067DF3)
            )
        }

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