package com.example.projectnavbottom.ui.theme

import android.widget.Button
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    backColor: Color,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
){
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(size = 15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backColor
        )
    ){
        Box(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 3.dp)
        ){
            content()
        }

    }
}

