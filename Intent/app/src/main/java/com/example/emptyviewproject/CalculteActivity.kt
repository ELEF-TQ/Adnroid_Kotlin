package com.example.emptyviewproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CalculateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_calculate)

            val nbr1 = intent.getIntExtra("Nbr1", 0)
            val nbr2 = intent.getIntExtra("Nbr2", 0)

            // Populate the EditText fields with the values of Nbr1 and Nbr2
            val nbr1EditText = findViewById<EditText>(R.id.NBR1)
            val nbr2EditText = findViewById<EditText>(R.id.NBR2)
            nbr1EditText.setText(nbr1.toString())
            nbr2EditText.setText(nbr2.toString())

            // Perform the calculation
            val result = nbr1 + nbr2

            Log.d("CalculateActivity", "Result: $result")

            val okButton = findViewById<Button>(R.id.OKReturn)
            okButton.setOnClickListener {
                // Return the result to the MainActivity
                val resultIntent = Intent()
                resultIntent.putExtra("Result", result)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        } catch (e: Exception) {
            Log.e("CalculateActivity", "Error in onCreate", e)
            // Handle the error gracefully, for example:
            // Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
            // finish() // Optionally, you might want to finish the activity to prevent further issues
        }
    }
}
