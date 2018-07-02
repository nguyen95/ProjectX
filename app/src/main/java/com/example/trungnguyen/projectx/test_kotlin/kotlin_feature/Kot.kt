package com.example.trungnguyen.projectx.test_kotlin.kotlin_feature

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.trungnguyen.projectx.R
import kotlinx.android.synthetic.main.layout_test_kotlin.*
import timber.log.Timber

/**
 * Created by Trung Nguyen on 16-Jun-18.
 */
class Kot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_test_kotlin)

        tv_test.setOnClickListener {
            v -> tv_test.text = "click"
            Timber.e("clcikkkkk")
        }


    }
}