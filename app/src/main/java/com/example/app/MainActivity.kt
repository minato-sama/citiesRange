package com.example.app

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.example.app.R.*
import com.google.gson.Gson
import java.io.InputStreamReader

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var gson: Gson
    private lateinit var citiesArray: Array<City>
    private lateinit var citiesDistanceText: EditText
    private var citiesName = ""
    private var citiesIndex = -1
    private var distance = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        supportActionBar?.hide()
        gson = Gson()
        val spinner = findViewById<Spinner>(id.spinner)
        val citiesList = resources.openRawResource(raw.cities)
        citiesDistanceText = findViewById(id.distance)
        citiesArray = gson.fromJson(InputStreamReader(citiesList), Cities::class.java).movies
        val citiesName: ArrayList<String> = ArrayList()

        for (city in citiesArray) {
            citiesName.add(city.name)
        }

        val adapter = ArrayAdapter(this, layout.support_simple_spinner_dropdown_item, citiesName)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    fun cityOnCLickEvent(view: View?) {
        var userInput = citiesDistanceText.text.toString().trim()

        if (userInput.isEmpty())
            userInput = "0"

        distance = userInput.toDouble()
        val lat: Double = citiesArray[citiesIndex].coord.lat.toDouble()
        val lon: Double = citiesArray[citiesIndex].coord.lon.toDouble()
        val currentCity: String = citiesArray[citiesIndex].name
        val suitableCites: ArrayList<String> = ArrayList()
        for (city in citiesArray) {
            val results = FloatArray(1)
            Location.distanceBetween(city.coord.lat.toDouble(), city.coord.lon.toDouble(), lat, lon, results)
            if (city.name != currentCity && results[0] <= distance) {
                suitableCites.add(city.name)
            }
        }
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("GameActivity", suitableCites)
        startActivity(intent)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        citiesName = parent?.getItemAtPosition(position).toString()
        citiesIndex = position
    }
}
