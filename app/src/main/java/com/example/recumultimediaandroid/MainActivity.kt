package com.example.recumultimediaandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import java.util.Random
/*
Crea una aplicación en android y kotlin que:

a) La primera Activity se compone de 3 botones (Añadir Texto, Añadir Número, Siguiente (desactivado)) y un text view. (1 punto) HECHO!!
b) Cada vez que el usuario presione el botón Añadir texto, el text view muestra el texto que tuviera más "Click hecho!". Después del primer click, el botón Siguiente queda activado. (1 punto) HECHO!!
c) Cada vez que el usuario presione el botón Añadir número, el text view muestra el texto que tuviera más un número aleatorio del 0 al 9. (1 punto) HECHO!!
d) Cuando el usuario pulsa el botón siguiente, se abre una segunda activity. (1 punto) HECHO!!


 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.tv1)
        val button1 = findViewById<Button>(R.id.boton1)
        val button2 = findViewById<Button>(R.id.boton2)
        val button3 = findViewById<Button>(R.id.boton3)
        val numero = (0..9).random()

        textView.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                    button3.isEnabled = button1.isClickable && button2.isClickable
            }
        })

        button1.setOnClickListener {
            textView.setText("${textView.text}, Click Hecho!!")
        }

        button2.setOnClickListener {
            textView.setText("${textView.text}, $numero")
        }

        button3.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }



}