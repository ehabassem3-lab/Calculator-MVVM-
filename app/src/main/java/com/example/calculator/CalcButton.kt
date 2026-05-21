package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CalcButton(
    operation: Int,
    onOperationClick: () -> Unit
) {
    Row  (
        modifier = Modifier
            .width(70.dp)
            .height(100.dp)

            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color.Black)
            .clickable { onOperationClick() } ,
         verticalAlignment = Alignment.CenterVertically ,
        horizontalArrangement = Arrangement.Center

    ) {
        Text(
            text = operation.toString(),
            color = Color.White
        )
    }
}