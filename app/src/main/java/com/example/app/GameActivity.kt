package com.example.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private lateinit var citiesArray: ArrayList<String>
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar?.hide()
        this.setTitle(getString(R.string.listCities))

        citiesArray = intent.getSerializableExtra("GameActivity") as ArrayList<String>
        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, citiesArray)
        spinner.adapter = adapter

        if (citiesArray.size == 0) {
            val showEnd = Toast.makeText(this,"it's gone", Toast.LENGTH_SHORT).show()
        } else {
            val showStart = Toast.makeText(this, "that's all", Toast.LENGTH_SHORT).show()

        }
    }

    fun mainIntentEvent(view: android.view.View) {
        val button = findViewById<Button>(R.id.activityMainIntent)
        val intent2 = Intent(this, MainActivity::class.java)
        intent.putExtra("MainActivity", true)
        startActivity(intent2)
    }
}