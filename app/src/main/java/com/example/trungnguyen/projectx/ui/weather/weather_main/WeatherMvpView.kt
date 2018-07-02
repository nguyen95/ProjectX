package com.example.trungnguyen.projectx.ui.weather.weather_main

import android.content.Context
import com.example.trungnguyen.projectx.base.baseMvpView.MvpView
import net.aksingh.owmjapis.model.CurrentWeather

/**
 * Created by Trung Nguyen on 21-Jun-18.
 */
interface WeatherMvpView : MvpView {
    fun showLoading()
    fun hideLoading()
    fun updateWeatherInfo(currentWeather: CurrentWeather?)
}