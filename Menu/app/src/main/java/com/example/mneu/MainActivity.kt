package com.example.mneu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        textView.visibility = TextView.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_item1 -> {
                displayMessage("Home Clicked")
                true
            }
            R.id.action_item2 -> {
                displayMessage("Main Clicked")
                true
            }
            R.id.action_item3 -> {
                displayMessage("Contact Clicked")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displayMessage(message: String) {
        textView.text = message
        textView.visibility = TextView.VISIBLE
    }
}
