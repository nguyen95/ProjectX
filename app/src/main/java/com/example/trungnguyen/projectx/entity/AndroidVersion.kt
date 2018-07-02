package com.example.trungnguyen.projectx.entity

import com.google.gson.Gson
import java.util.jar.Attributes

/**
 * Created by Trung Nguyen on 26-Apr-18.
 */
data class AndroidVersion(var name: String, var ver: String, var api: String){
    override fun toString(): String {
        return Gson().toJson(this)
    }
}