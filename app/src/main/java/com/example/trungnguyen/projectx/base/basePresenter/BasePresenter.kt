package com.example.trungnguyen.projectx.base.basePresenter

import android.os.AsyncTask
import com.example.trungnguyen.projectx.base.baseMvpView.MvpView
import java.util.ArrayList

/**
 * Created by Trung Nguyen on 18-May-18.
 */
open class BasePresenter<V : MvpView> : Presenter<V> {

    private var mvpViev : V? = null

    override fun attachView(view: V) {
        mvpViev = view
    }

    override fun detachView() {
        mvpViev = null
    }

    fun getMvpView() : V? = mvpViev

}