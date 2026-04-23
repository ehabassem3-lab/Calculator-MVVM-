package com.example.calculator

import androidx.collection.emptyLongSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.toString


class  CalcViewModel() : ViewModel(){

    var display: MutableLiveData<String> = MutableLiveData("")
    var maindisplay : MutableLiveData<String> = MutableLiveData("")
    var LH: Double = 0.0
    var RH: Double = 0.0
    var Operation: String = ""
     var isNewNumber  = false
    fun append(num: Int): String {
        if (!isNewNumber){
            var current = display.value ?: ""
            maindisplay.value = current + num.toString()
            display.value = current + num.toString()
            return  display.value?:""
        }
        else{
            var current = display.value ?: ""
            maindisplay.value = maindisplay.value + num.toString()
            display.value = current + num.toString()
            return  display.value?:""

        }


         return ""

    }
    fun operationClick(op: String) {
        LH = display.value?.toDoubleOrNull() ?: 0.0
        Operation = op
        maindisplay.value = display.value + op
        display.value = ""
        isNewNumber = true

    }
    fun calculate(): Double {
        RH = display.value?.toDoubleOrNull() ?:0.0
         val result = when(Operation){
             "+" -> RH+LH
             "-" ->LH-RH
              "/" ->LH/RH
              "*" -> LH*RH

                 else-> 0.0
         }
        display.value = result.toString()
        maindisplay.value = result.toString()

        return result
    }
    fun clear() {
        display.value = ""
        LH = 0.0
       RH = 0.0
        Operation = ""
        maindisplay.value= ""
    }



}
