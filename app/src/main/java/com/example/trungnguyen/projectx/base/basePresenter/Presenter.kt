package com.example.trungnguyen.projectx.base.basePresenter

import com.example.trungnguyen.projectx.base.baseMvpView.MvpView

/**
 * Created by Trung Nguyen on 18-May-18.
 */
interface Presenter<V : MvpView> {
    fun attachView(view: V);
    fun detachView();
}