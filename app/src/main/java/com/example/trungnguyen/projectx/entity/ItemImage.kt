package com.example.trungnguyen.projectx.entity

import com.google.gson.Gson

/**
 * Created by Trung Nguyen on 25-May-18.
 */
data class ItemImage(
        var id: Int,
        var path: String
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}