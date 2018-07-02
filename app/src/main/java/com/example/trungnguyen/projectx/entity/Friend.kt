package com.example.trungnguyen.projectx.entity

import com.google.gson.Gson

/**
 * Created by Trung Nguyen on 24-May-18.
 */
data class Friend(
        var username1: String,
        var username2: String,
        var stt: Int
){
    override fun toString(): String {
        return Gson().toJson(this)
    }
}