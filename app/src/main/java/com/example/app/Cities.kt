package com.example.app

import com.google.gson.annotations.SerializedName

data class Cities(
    @SerializedName("cities") val movies: Array<City>
)