package com.example.trungnguyen.projectx.testMvp

import android.app.Activity
import android.graphics.Color
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.example.trungnguyen.projectx.base.basePresenter.BasePresenter
import com.example.trungnguyen.projectx.entity.AndroidVersion
import com.example.trungnguyen.projectx.entity.Hello
import com.example.trungnguyen.projectx.repository.Repository
import com.example.trungnguyen.projectx.service.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.layout_test_mvp_activity.*
import net.aksingh.owmjapis.model.CurrentWeather
import java.util.*
import java.util.logging.Handler
import kotlin.concurrent.timerTask
import com.example.trungnguyen.projectx.base.baseALertDialog.LoadingDialog
import android.R.attr.path
import com.example.trungnguyen.projectx.constants.Constants.API_KEY
import net.aksingh.owmjapis.core.OWM
import timber.log.Timber.e


/**
 * Created by Trung Nguyen on 18-May-18.
 */
class TestMvpPresenter : BasePresenter<TestMvpView>() {

    fun getUser() {
        getMvpView()?.showLoading()
        Repository.createService(ApiService::class.java).getAndroidVersion()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result -> loadSuccess1(result) },
                        { error -> loadError(error) }
                )
    }

    private fun loadError(error: Throwable) {
        Log.e("Error ", "loi roi")
        getMvpView()?.hideLoading()
    }

    private fun loadSuccess1(result: List<AndroidVersion>) {
        Log.e("Success ", "" + result.size)
        getMvpView()?.hideLoading()
        getMvpView()?.showUserInfo(result)
    }

    fun getWeatherInfo(city: String, country: OWM.Country) {
        getMvpView()?.showLoading()
        val task = object : AsyncTask<Void, Void, CurrentWeather?>() {
            internal var error = false

            override fun doInBackground(vararg voids: Void): CurrentWeather? {

                try {
                    var owm = OWM(API_KEY)
                    return owm.currentWeatherByCityName(city, country)
                } catch (e: Exception) {
                    e.printStackTrace()
                    e(e.message)
                    error = true
                }
                return null
            }

            override fun onPostExecute(result: CurrentWeather?) {
                getMvpView()?.hideLoading()
                if (error) {
                    e("nulllllllll")
                    getMvpView()?.showError()
                    return
                } else {
                    e("not nulllllllll "+result.toString())
                    getMvpView()?.showWeather(result)
                }
            }
        }
        task.execute()
    }
}