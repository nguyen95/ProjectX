package com.example.trungnguyen.projectx.ui.dating.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import com.example.trungnguyen.projectx.R
import com.example.trungnguyen.projectx.base.baseALertDialog.LoadingDialog
import com.example.trungnguyen.projectx.entity.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainMvpView, View.OnClickListener {
    override fun onClick(p0: View?) {
        MainPresenter.getUser()
    }

    override fun showLoading() {
        LoadingDialog.show(this)
    }

    override fun hideLoading() {
        LoadingDialog.hide()
    }

    private lateinit var MainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainPresenter = MainPresenter()
        MainPresenter.attachView(this)

        var android_id = android.provider.Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID).toString()
        Log.e("SHA-1111",android_id)
        tv_hello.setOnClickListener(this)
    }

    override fun loadUserSuccess(user: User) {
        tv_hello.text = "ahhhhhhhhh"
    }

}
