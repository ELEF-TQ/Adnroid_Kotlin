package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var editTextInput1: EditText
    private lateinit var editTextInput2: EditText
    private lateinit var editTextResult: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonSubtract: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonEquals: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        // Initialize EditText and Button views
        editTextInput1 = findViewById(R.id.editTextText2)
        editTextInput2 = findViewById(R.id.editTextText)
        editTextResult = findViewById(R.id.editTextText3)
        buttonAdd = findViewById(R.id.button3)
        buttonSubtract = findViewById(R.id.button4)
        buttonMultiply = findViewById(R.id.button5)
        buttonEquals = findViewById(R.id.button6)

        // Set click listeners for the buttons
        buttonAdd.setOnClickListener { calculateResult('+') }
        buttonSubtract.setOnClickListener { calculateResult('-') }
        buttonMultiply.setOnClickListener { calculateResult('*') }
        buttonEquals.setOnClickListener { calculateResult('/') }
    }

    private fun calculateResult(operator: Char) {
        val num1 = editTextInput1.text.toString().toDouble()
        val num2 = editTextInput2.text.toString().toDouble()
        val result: Double = when (operator) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> num1 / num2
            else -> 0.0
        }
        editTextResult.setText(result.toString())
    }
}
