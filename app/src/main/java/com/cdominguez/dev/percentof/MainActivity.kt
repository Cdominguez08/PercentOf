package com.cdominguez.dev.percentof

import android.content.Context
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG : String = "MAIN_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnCalculate.setOnClickListener{
            calculatePercent()
        }


        edQuant.setOnKeyListener{ view, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_ENTER){
                calculatePercent()
            }
            false
        }

        btnClean.setOnClickListener{

            clean()
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun clean() {

        cvDesc.visibility = View.GONE
        edQuant.setText("")
        edPerc.setText("")
        edQuant.clearFocus()
        edPerc.clearFocus()
    }

    fun calculatePercent(){

        if (edPerc.text.isNullOrEmpty()){

            Toast.makeText(this,getText(R.string.bannerErrorPerc),Toast.LENGTH_SHORT).show()
            return
        }

        if (edQuant.text.isNullOrEmpty()){

            Toast.makeText(this,getText(R.string.bannerErrorQuant),Toast.LENGTH_SHORT).show()
            return
        }

        edQuant.hideKeyboard()
        edPerc.hideKeyboard()

        try {

            var percent: Double = edPerc.text.toString().toDouble()
            var quant: Double = edQuant.text.toString().toDouble()
            var p100 : Double = 100.00
            var total : Double = 0.0
            var totalLess : Double = 0.0
            var totalPlus : Double = 0.0

            total = percent / p100
            Log.i(TAG, "total " + total)

            total = total * quant
            Log.i(TAG, "total " + total)

            totalLess = quant - total
            totalPlus = quant + total

            cvDesc.visibility = View.VISIBLE

            txtPerc.text = total.toString()

            txtQuant1.text = quant.toString()
            txtPercLess.text = "- " + percent.toString() + "%"
            txtTotalLess.text = totalLess.toString()

            txtQuant2.text = quant.toString()
            txtPercPlus.text = "+ " + percent.toString() + "%"
            txtTotalPlus.text = totalPlus.toString()
        }catch (e : NullPointerException){

            Toast.makeText(this,"Error: " + e.localizedMessage,Toast.LENGTH_SHORT).show()
        }

    }
}
