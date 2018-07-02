package com.example.trungnguyen.projectx.entity.weather

import com.google.gson.Gson

/**
 * Created by Trung Nguyen on 18-Jun-18.
 */
data class WeatherMain(var city: City,
                       var cod: String,
                       var message: String,
                       var cnt: Int,
                       var list: ArrayList<List>) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}