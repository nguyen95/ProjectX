package com.example.trungnguyen.projectx.ui.dating.main

import com.example.trungnguyen.projectx.base.baseMvpView.MvpView
import com.example.trungnguyen.projectx.entity.User

/**
 * Created by Trung Nguyen on 30-May-18.
 */
interface MainMvpView : MvpView{
    fun showLoading()
    fun hideLoading()
    fun loadUserSuccess(user: User){
    }
}