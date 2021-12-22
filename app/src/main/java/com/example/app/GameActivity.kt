package com.example.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private lateinit var citiesArray: ArrayList<String>
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar?.hide()
        this.setTitle(getString(R.string.listCities))
        val messageTextView = findViewById<TextView>(R.id.messageTextView)
        citiesArray = intent.getSerializableExtra("GameActivity") as ArrayList<String>
        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, citiesArray)
        spinner.adapter = adapter
        if (citiesArray.size == 0) {
            messageTextView.text = "no city in range"
        } else {
            messageTextView.text = "result"
        }
    }
}