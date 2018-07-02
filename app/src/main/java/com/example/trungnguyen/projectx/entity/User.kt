package com.example.trungnguyen.projectx.entity

import com.google.gson.Gson

/**
 * Created by Trung Nguyen on 24-May-18.
 */
data class User(
        var username: String,
        var password: String,
        var nickname: String,
        var address: String?,
        var hobby: String?,
        var img: String?,
        var check: Int = 0
){
    constructor() : this("","","","","","",0)

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
