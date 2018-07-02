package com.example.trungnguyen.projectx.service

import com.example.trungnguyen.projectx.entity.AndroidVersion
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Trung Nguyen on 26-Apr-18.
 */
interface ApiService {
    @GET("android/jsonarray/")
    fun getAndroidVersion(): Observable<List<AndroidVersion>>

    @GET("android/jsonarray/")
    fun getString(): Observable<List<String>>
}