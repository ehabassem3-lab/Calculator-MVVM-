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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculatorMainUi( ){
    var Numbers = listOf<Int>(
        1, 2, 3, 4, 5, 6, 7, 8, 9,0
    )
    var Opraions = listOf<String>(
        "+","-","/","*"
    )
    val viewModel = viewModel<CalcViewModel>()

    val display = viewModel.maindisplay.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.3f)
                .background(Color.Black),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = display.value,
                fontSize = 40.sp,
                color = Color.White
            )
        }
        Spacer(Modifier.size(20.dp))

        Row() {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(4),
                modifier = Modifier.size(200.dp).padding(start = 20.dp) ,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),

            ) {
                items(Numbers) { number ->
                    CalcButton(operation = number) {
                        viewModel.append(number)
                    }
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier.fillMaxWidth(.3f).height(200.dp) ,
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),

            ) {
                items(Opraions) { operation ->
                    CalcOperation(operation = operation) {
                        viewModel.operationClick(operation)
                    }
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Black)
                    .clickable {
                        viewModel.calculate()

                    }
            ) {
                Text(
                    text = "=" ,
                    color = Color.White
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Black)
                    .clickable { viewModel.clear() }
            ) {
                Text(
                    text = "C" ,
                    color = Color.White
                )
            }
        }



    }


}