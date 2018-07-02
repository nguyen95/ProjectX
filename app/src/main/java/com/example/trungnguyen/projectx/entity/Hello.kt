package com.example.trungnguyen.projectx.entity

/**
 * Created by Trung Nguyen on 18-May-18.
 */
data class Hello(var title: String?, var content: String?, var rate: Int?){
    override fun toString(): String {
        return "Hello(title='$title', content='$content', rate=$rate)"
    }
    
}