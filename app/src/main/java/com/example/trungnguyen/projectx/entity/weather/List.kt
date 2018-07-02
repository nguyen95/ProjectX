package com.example.trungnguyen.projectx.entity.weather

import com.google.gson.Gson

/**
 * Created by Trung Nguyen on 18-Jun-18.
 */
data class List(var dt: Long,
                var main: Main,
                var weather: ArrayList<Weather>,
                var clouds: Clouds,
                var wind: Wind,
                var rain: Rain,
                var snow: Snow,
                var dt_txt: String) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}