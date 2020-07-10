package com.example.simplecalcuator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var viewModelCalculator : MyViewModelCalci
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelCalculator = ViewModelProviders.of(this).get(MyViewModelCalci::class.java)
        values.text = viewModelCalculator.getValue()
        result.text = viewModelCalculator.getResult()


        one.setOnClickListener{appendValues("1",true)}
        two.setOnClickListener{appendValues("2",true)}
        three.setOnClickListener{appendValues("3",true)}
        four.setOnClickListener{appendValues("4",true)}
        five.setOnClickListener{appendValues("5",true)}
        six.setOnClickListener{appendValues("6",true)}
        seven.setOnClickListener{appendValues("7",true)}
        eight.setOnClickListener{appendValues("8",true)}
        nine.setOnClickListener{appendValues("9",true)}
        zero.setOnClickListener{appendValues("0",true)}
        dot.setOnClickListener{appendValues(".",true)}

        openbracket.setOnClickListener{appendValues("(",false)}
        closebracket.setOnClickListener{appendValues(")",false)}
        divide.setOnClickListener{appendValues("/",false)}
        multiply.setOnClickListener{appendValues("*",false)}
        difference.setOnClickListener{appendValues("-",false)}
        add.setOnClickListener{appendValues("+",false)}

        allclear.setOnClickListener(){
            viewModelCalculator.setValue(null,false)
            values.text = viewModelCalculator.getValue()
//            values.text = ""

            viewModelCalculator.setResult(null)
            result.text =   viewModelCalculator.getResult()
        }

        backspace.setOnClickListener(){
            val currentValues = viewModelCalculator.getValue()
            if (currentValues.isNotEmpty()){
                viewModelCalculator.setValue(currentValues.substring(0,currentValues.length-1),true)
                values.text = viewModelCalculator.getValue()
            }
            viewModelCalculator.setResult(null)
            result.text =   viewModelCalculator.getResult()
        }

        equals.setOnClickListener(){
            try {
                val makeExpression = ExpressionBuilder(viewModelCalculator.getValue()).build()
                val finalResult = makeExpression.evaluate()
                val longResult = finalResult.toLong()
                if (finalResult == longResult.toDouble()){
                    viewModelCalculator.setResult(longResult.toString())
                }
                else{
                    viewModelCalculator.setResult(finalResult.toString())
                }
                result.text = viewModelCalculator.getResult()
            }catch (e:Exception){
                viewModelCalculator.setResult("Error")
                result.text = viewModelCalculator.getResult()
            }
        }
    }

//    override fun onAttachedToWindow() {
//        super.onAttachedToWindow()
//        viewModelCalculator = ViewModelProviders.of(this).get(MyViewModelCalci::class.java)
//    }

    fun appendValues(value: String, check: Boolean ) {
//        viewModelCalculator = ViewModelProviders.of(this).get(MyViewModelCalci::class.java)
        if (result.text=="Error"){
            viewModelCalculator.setValue(null, false)
            values.text = viewModelCalculator.getValue()
            viewModelCalculator.setResult(null)
            result.text =   viewModelCalculator.getResult()
        }

        if (values.text.isEmpty() && !check){
            viewModelCalculator.setValue("0",false)
            values.text = viewModelCalculator.getValue()
        }

        if (result.text.isNotEmpty()){
            viewModelCalculator.setValue(null,false)
            values.text = viewModelCalculator.getValue()
        }

        if (check){
            viewModelCalculator.setResult(null)
            result.text =   viewModelCalculator.getResult()
            viewModelCalculator.setValue(value,false)
            values.text = viewModelCalculator.getValue()
        }
        else{
            viewModelCalculator.setValue(result.text.toString(),false)
            viewModelCalculator.setValue(value,false)
            values.text = viewModelCalculator.getValue()
//            values.append(result.text)
//            values.append(value)
            viewModelCalculator.setResult(null)
            result.text =   viewModelCalculator.getResult()
        }
    }

}