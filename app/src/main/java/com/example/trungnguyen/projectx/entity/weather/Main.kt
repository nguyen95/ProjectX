package com.example.trungnguyen.projectx.entity.weather

import com.google.gson.Gson

/**
 * Created by Trung Nguyen on 18-Jun-18.
 */
data class Main(var temp: Double,
                var temp_min: Double,
                var temp_max: Double,
                var pressure: Double,
                var sea_level: Double,
                var grnd_level: Double,
                var humidity: Int,
                var temp_kf: Double) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}