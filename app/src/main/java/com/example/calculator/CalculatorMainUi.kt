package com.example.calculator

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculatorMainUi() {

    val viewModel = viewModel<CalcViewModel>()
    val display = viewModel.maindisplay.observeAsState("0")

    val buttons = listOf(
        "C", "⌫", "%", "/",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        ".", "0", "=", "Ans"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = display.value,
                fontSize = 52.sp,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {

            items(buttons) { button ->

                if (button.isNotEmpty()) {

                    val buttonColor = when (button) {
                        "C", "⌫", "%" -> Color(0xFFA5A5A5)
                         "/", "×", "-", "+", "=" ,"Ans"-> Color(0xFFFF9F0A)
                        else -> Color(0xFF333333)
                    }

                    val textColor = when (button) {
                        "C", "⌫", "%" -> Color.Black
                        else -> Color.White
                    }

                    Box(
                        modifier = Modifier
                            .size(85.dp)
                            .background(
                                color = buttonColor,
                                shape = CircleShape
                            )
                            .clickable {

                              when(button){
                                  ".","1" , "2" ,"3",  "4" , "5" ,"6",  "7" , "8" ,"9" , "0"->{
                                      viewModel.append(button)
                                  }
                                  "⌫" ->{
                                      viewModel.clearLast()
                                  }
                                  "C"->{
                                      viewModel.clear()
                                  }
                                  "="->{
                                      viewModel.calculate()
                                      println(viewModel.LH )
                                      println(viewModel.RH)
                                   }
                                  "Ans" ->{
                                      viewModel.useAns()
                                  }

                                  else -> {
                                      viewModel.operationClick(button)
                                  }



                              }

                            },
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = button,
                            fontSize = 28.sp,
                            color = textColor
                        )
                    }
                }
            }
        }
    }
}




