package com.example.trungnguyen.projectx.ui.dating.main

import com.example.trungnguyen.projectx.base.basePresenter.BasePresenter
import com.example.trungnguyen.projectx.entity.User

/**
 * Created by Trung Nguyen on 30-May-18.
 */
class MainPresenter : BasePresenter<MainMvpView>() {
    fun getUser() {
        getMvpView()?.showLoading()
        ////....
        var User: User
        User = User("nguyenkute","12345678","Ốc nhồi","Ninh Binh",
                "đá bóng, chơi game, nghe nhạc,...",null,0)

        getMvpView()?.hideLoading()
        getMvpView()?.loadUserSuccess(User)
    }
}