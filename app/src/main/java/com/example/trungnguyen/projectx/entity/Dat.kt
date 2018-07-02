package com.example.trungnguyen.projectx.entity

import com.google.gson.Gson

/**
 * Created by Trung Nguyen on 25-May-18.
 */
data class Dat(
        var id: String,
        var username: String,
        var username2: String,
        var title: String,
        var content: String,
        var place: String,
        var time: String,
        var pub_date: String,
        var check: Int,
        var check2: Int,
        var img: String,
        var nickname: String
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}