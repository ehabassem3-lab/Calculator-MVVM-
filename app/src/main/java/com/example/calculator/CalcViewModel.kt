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
    var Ans : Double = 0.0
    var Operation: String = ""
     var isNewNumber  = false
    var isCalculated = false
    fun append(num: String): String {

        if (isCalculated) {

            display.value = ""
            maindisplay.value = ""
            isCalculated = false
        }

        if (isNewNumber) {

            display.value = ""

            maindisplay.value =
                (maindisplay.value ?: "") + num

            display.value = num

            isNewNumber = false

            return display.value ?: ""
        }

        val current = display.value ?: ""

        display.value = current + num
        maindisplay.value =
            (maindisplay.value ?: "") + num

        return display.value ?: ""
    }
    fun operationClick(op: String) {

        val current = display.value?.toDoubleOrNull() ?: 0.0

        if (Operation.isNotEmpty() && !isNewNumber) {

            RH = current

            LH = when (Operation) {
                "+" -> LH + RH
                "-" -> LH - RH
                "/" -> LH / RH
                "×" -> LH * RH
                else -> LH
            }

            display.value = LH.toString()
        } else {
            LH = current
        }

        Operation = op
        isNewNumber = true
        isCalculated = false

        maindisplay.value = (maindisplay.value ?: "") + op
    }
    fun calculate(): Double {

        val current = display.value?.toDoubleOrNull() ?: 0.0

        RH = current

        LH = when (Operation) {
            "+" -> LH + RH
            "-" -> LH - RH
            "/" -> LH / RH
            "×" -> LH * RH
            else -> current
        }

        display.value = LH.toString()
        maindisplay.value = LH.toString()

        Ans = LH
        isCalculated = true
        isNewNumber = false
        Operation = ""

        return LH
    }
    fun clear() {
        display.value = ""
        LH = 0.0
       RH = 0.0
        Operation = ""
        maindisplay.value= ""
    }
    fun clearLast() {
        val current = display.value ?: ""

        if (current.isNotEmpty()) {
            display.value = current.dropLast(1)
            maindisplay.value = maindisplay.value?.dropLast(1)
        } else if (Operation.isNotEmpty()) {
            Operation = ""
            maindisplay.value = maindisplay.value?.dropLast(1)
            isNewNumber = false
        }
        else{
            display.value = current.dropLast(1)
            maindisplay.value = maindisplay.value?.dropLast(1)
        }
    }

    fun useAns() {

        if (isCalculated) {

            display.value = ""
            maindisplay.value = ""

            isCalculated = false
        }

        display.value = Ans.toString()
        maindisplay.value = (maindisplay.value ?: "") + Ans.toString()

        isNewNumber = false
    }

}
