package com.example.simplecalcuator

import androidx.lifecycle.ViewModel

class MyViewModelCalci : ViewModel() {
    var a : String = ""
    var b : String = ""

    fun setValue(values: String?, setEqual : Boolean){
        if (values==null){
            a = ""
        }
        else if (values=="0" || setEqual){
            a = values
        }
        else{
            a += values
        }
    }

    fun getValue() : String{
        return a
    }

    fun setResult(result: String?){
        if (result == null) {
            b = ""
        }
        else{
            b = result
        }
    }

    fun getResult() : String{
        return b
    }
}