package com.example.trungnguyen.projectx.entity

import com.google.gson.Gson

/**
 * Created by Trung Nguyen on 25-May-18.
 */
data class Sms(
        var id: Int,
        var username1: String,
        var username2: String,
        var content: String,
        var date: String,
        var check: Int,
        var img: String,
        var nickname: String
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}