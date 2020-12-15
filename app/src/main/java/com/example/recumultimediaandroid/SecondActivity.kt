package com.example.recumultimediaandroid

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.text.InputFilter
import android.text.Spanned
import android.os.Build
import androidx.core.view.isVisible

/*
e) la segunda activity se compone de un botón (desactivado), un editText y un textView. (1 punto ) HECHO!!
d) El editText solo acepta número del 0 al 9. Únicamente si hay un número metido, el botón se activa. (2 punto) HECHO!!
f) Si se pulsa el botón se divide el string por el número seleccionado. Por ejemplo: si el número es 1 El resultado se muestra en el textView es: C l i c k h e c h o ! (3 punto)
*/

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val editText = findViewById<EditText>(R.id.et)
        val textView = findViewById<TextView>(R.id.tv2)
        val button = findViewById<Button>(R.id.boton4)
        editText.inputFilterNumberRange(0..9)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
             button.isEnabled=editText.text.isNotEmpty()
             textView.isVisible=button.isClickable
            }

        })
    }
}

fun EditText.inputFilterNumberRange(range: IntRange){
    filterMin(range.first)
    filters = arrayOf<InputFilter>(InputFilterMax(range.last))
}

class InputFilterMax(private var max: Int) : InputFilter {
    override fun filter(
        p0: CharSequence, p1: Int, p2: Int, p3: Spanned?, p4: Int, p5: Int
    ): CharSequence? {
        try {
            val replacement = p0.subSequence(p1, p2).toString()
            val newVal = p3.toString().substring(0, p4) + replacement + p3.toString()
                .substring(p5, p3.toString().length)
            val input = newVal.toInt()
            if (input <= max) return null
        } catch (e: NumberFormatException) {
        }
        return ""
    }
}

fun EditText.filterMin(min: Int){
    onFocusChangeListener = View.OnFocusChangeListener { view, b ->
        if (!b) {
            // set minimum number if inputted number less than minimum
            setTextMin(min)
        }
    }

    setOnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            // set minimum number if inputted number less than minimum
            setTextMin(min)
        }
        false
    }
}

fun EditText.setTextMin(min: Int){
    try {
        val value = text.toString().toInt()
        if (value < min){
            setText("$min")
        }
    }catch (e: Exception){
        setText("$min")
    }
}