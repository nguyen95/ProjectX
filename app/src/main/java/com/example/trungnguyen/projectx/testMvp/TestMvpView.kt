package com.example.trungnguyen.projectx.testMvp

import com.example.trungnguyen.projectx.base.baseMvpView.MvpView
import com.example.trungnguyen.projectx.entity.AndroidVersion
import net.aksingh.owmjapis.model.CurrentWeather

/**
 * Created by Trung Nguyen on 18-May-18.
 */
interface TestMvpView : MvpView {
    fun showLoading()
    fun hideLoading()
    fun showError()
    fun showWeather(result: CurrentWeather?)
    fun showUserInfo(result: List<AndroidVersion>)

    /// a default method in kotlin (java 8)
    // this methods CAN BE (or not) called from all classes implementing this interface
    fun showUserInfo2(result: List<AndroidVersion>){
        println("jikokokiki")
    }
}