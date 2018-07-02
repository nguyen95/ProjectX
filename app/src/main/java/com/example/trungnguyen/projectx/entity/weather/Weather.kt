package com.example.trungnguyen.projectx.entity.weather

import com.google.gson.Gson

/**
 * Created by Trung Nguyen on 18-Jun-18.
 */
data class Weather(var id: Int, var main: String, var description: String, var icon: String) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}