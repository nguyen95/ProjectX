package com.example.trungnguyen.projectx.test_kotlin.kotlin_feature

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.trungnguyen.projectx.R
import com.example.trungnguyen.projectx.test_kotlin.java_feature.Activity
import kotlinx.android.synthetic.main.layout_test_kotlin.*
import timber.log.Timber.e
import java.math.BigDecimal
import java.util.*

/**
 * Created by Trung Nguyen on 15-Jun-18.
 */
class Activity : AppCompatActivity() {

    val Int.bd: BigDecimal get() = bd
    var Entity.st: String
        get() = st
        set(value) {
            st = value
        }

    companion object {
        fun main(args: Array<String>) {
            var a: String? = null
            val aasa= "aaa"
            println(a?.length)
            println(a!!.length)  /// exception

            var java = com.example.trungnguyen.projectx.test_kotlin.java_feature.Entity()

            var b = "ddd"
//            b = null  /// error

            var Entity = Entity(2 , "")
            print(Entity.i)
            Entity.i = 4

            val arr = arrayListOf(-42, 17, 13, -9, 12)
            val arr2 = arr.filter { it >= 0 }
            println(arr2)
            listOf(1, 2, 3, 4)
                    .map { it * 10 }
                    .filter { it > 20 }
                    .forEach { print(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_test_kotlin)

        tv_test.setOnClickListener {
            v -> print("click")
            tv_test.text = "n"
        }
        tv_test.text = "b"
        print(tv_test.text)

        startActivity(Intent(this,Activity::class.java))

        f(s = "aa", i = 2)

        ////extension properties
//        var int = 12 as BigDecimal
//        var big: BigDecimal = int
//
//        var bigEx = 12.bd
//        var bigExTemp: BigDecimal = bigEx

        var en = Entity(2, "aaaa")
        en.i = 1
        en.st = " f "
        var i: String = en.st
        en.showAny()

        var a: String? = null
        print(a?.length)

        var e= Entity(1,"a")
        e.showAny()
        e.st = "aaaa"

        f(i = 3, s = "aaaa")
        f(s= "aaaa", i= 1)
        f(3, "aaa")


    }

    fun f(i: Int, s: String) {
//        e(s + i)

        e("hhhhhhh: $s $i" + s)
    }

    fun Entity.showAny(){
        e("extension functions")
    }

}