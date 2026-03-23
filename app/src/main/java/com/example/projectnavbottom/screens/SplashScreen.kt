package com.example.projectnavbottom.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectnavbottom.R
import com.example.projectnavbottom.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNextNavigate:()->Unit
) {
    val scale = remember { Animatable(1f) }
    val rotation = remember { Animatable(0f) }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape)
                .rotate(rotation.value)
                .scale(scale.value)
        )
    }


    LaunchedEffect(true) {

        rotation.animateTo(
            targetValue = 360f,
            animationSpec = tween(2000)
        )

        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(0.8f).getInterpolation(it)
                }
            )
        )

        delay(1500L)
        onNextNavigate()
    }

}