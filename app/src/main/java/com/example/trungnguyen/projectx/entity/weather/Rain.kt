package com.example.trungnguyen.projectx.entity.weather

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Created by Trung Nguyen on 18-Jun-18.
 */
data class Rain(@SerializedName("3h") var h3h: String) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}