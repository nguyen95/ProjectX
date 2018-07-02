package com.example.trungnguyen.projectx.entity.weather

import com.google.gson.Gson

/**
 * Created by Trung Nguyen on 18-Jun-18.
 */
data class City(var id: Int, var name: String, var coord: Coord, var country: String){
    override fun toString(): String {
        return Gson().toJson(this)
    }
}