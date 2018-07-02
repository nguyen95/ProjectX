package com.example.trungnguyen.projectx.entity.weather

import com.google.gson.Gson

/**
 * Created by Trung Nguyen on 18-Jun-18.
 */
data class Wind(var speed: Double, var deg: Double) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}