package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var lastNumeric:Boolean = false
    var tvUserInput = findViewById<TextView>(R.id.tvUserInput)
    var lastDot: Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun digit(view: View)
    {

        tvUserInput.append((((view as Button).text)))
        lastNumeric=true
    }

    fun clearClick(view: View)
    {
        tvUserInput.text =""
        lastNumeric=false
        lastDot = false
    }

    fun decimalPointClick(view: View)
    {
        if(lastNumeric&&!lastDot)
        {
            tvUserInput.append(".")
            lastNumeric=false
            lastDot = true
        }else if(!lastNumeric&&!lastDot)
        {
            tvUserInput.append(".")
            lastDot = true

        }
    }

    fun isEqual(view:View)
    {
        if (lastNumeric)
        {
            var tvValue = tvUserInput.text.toString()
            var prefix:String =""

            try
            {
                if (prefix.startsWith("-"))
                {
                    prefix="-"
                    tvValue = tvValue.substring(1)
                }

                // for subtraction
                if(tvValue.contains("-"))
                {
                    val splitValue = tvValue.split("-")

                    var splitOneValue = tvValue[0].toString()
                    var splitTwoValue = tvValue[1].toString()

                    if (!prefix.isEmpty())
                    {
                        splitOneValue = prefix + splitOneValue
                    }

                    tvUserInput.text = removeDecimalPoint((splitOneValue.toDouble()- splitTwoValue.toDouble()).toString())
                }
                // for addition
                else if(tvValue.contains("+"))
                {
                    val splitValue = tvValue.split("+")

                    var splitOneValue = tvValue[0]
                    var splitTwoValue = tvValue[1]

//                           if (!prefix.isEmpty())
//                           {
//                               splitOneValue = prefix + splitOneValue
//                           }

                    tvUserInput.text = removeDecimalPoint((splitOneValue.code.toDouble() + splitTwoValue.code.toDouble()).toString())

                }
                // for multiplication
                else if(tvValue.contains("x"))
                {
                    val splitValue = tvValue.split("x")

                    var splitOneValue = tvValue[0]
                    var splitTwoValue = tvValue[1]

//                            if (!prefix.isEmpty())
//                            {
//                                splitOneValue = prefix + splitOneValue
//                            }

                    tvUserInput.text = removeDecimalPoint((splitOneValue.code.toDouble() * splitTwoValue.code.toDouble()).toString())

                }
                // for division
                else if(tvValue.contains("/"))
                {
                    val splitValue = tvValue.split("/")

                    var splitOneValue = tvValue[0]
                    var splitTwoValue = tvValue[1]

//                            if (!prefix.isEmpty())
//                            {
//                                splitOneValue = prefix + splitOneValue
//                            }
//
                    tvUserInput.text = removeDecimalPoint((splitOneValue.code.toDouble() / splitTwoValue.code.toDouble()).toString())
                }
            }    catch (e:ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }


    fun onOperator(view: View)
    {
        if(lastNumeric && !isOperatorAdded(tvUserInput.text.toString()))
        {
            lastNumeric=false
            lastDot=false
        }
    }

    private fun isOperatorAdded(value:String):Boolean{
        return if (value.startsWith("-"))
        {
            false
        }else
            value.contains("/") || value.contains("+")|| value.contains("-")|| value.contains("x")
    }
    private fun removeDecimalPoint(removed:String):String
    {
        var value = removed
        if(value.contains(".0"))
        {
            value=removed.substring(0,removed.length-2)
            //12.0
        }
        return value
    }

}