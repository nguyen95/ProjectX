package com.example.trungnguyen.projectx.ui.weather.weather_main

import android.os.AsyncTask
import com.example.trungnguyen.projectx.base.basePresenter.BasePresenter
import com.example.trungnguyen.projectx.constants.Constants.API_KEY
import net.aksingh.owmjapis.core.OWM
import net.aksingh.owmjapis.model.CurrentWeather
import timber.log.Timber

/**
 * Created by Trung Nguyen on 21-Jun-18.
 */
class WeatherPresenter : BasePresenter<WeatherMvpView>() {

    private fun getWeatherInfo(lat: Double, long: Double){
        getMvpView()?.showLoading()
        val task = object : AsyncTask<Void, Void, CurrentWeather?>() {
            internal var error = false

            override fun doInBackground(vararg voids: Void): CurrentWeather? {

                try {
                    var owm = OWM(API_KEY)
                    return owm.currentWeatherByCoords(lat, long)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Timber.e(e.message)
                    error = true
                }
                return null
            }

            override fun onPostExecute(result: CurrentWeather?) {
                getMvpView()?.hideLoading()
                if (error) {
                    Timber.e("nulllllllll")
                    return
                } else {
                    Timber.e("not nulllllllll " + result.toString())
                    getMvpView()?.updateWeatherInfo(result)
                }
            }
        }
        task.execute()
    }
}