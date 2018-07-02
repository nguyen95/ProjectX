package com.example.trungnguyen.projectx.base.baseALertDialog

import android.app.AlertDialog
import android.content.Context
import com.example.trungnguyen.projectx.R

/**
 * Created by Trung Nguyen on 18-May-18.
 */
class LoadDialog(context: Context) : BaseAlertDialog(context) {
    override var alertDialog: AlertDialog? = null

    override fun initVariable() {

    }

    override fun isCancel(): Boolean {
        return false
    }

    override fun initData() {

    }

    override fun setContentView(): Int {
        return R.layout.loading_layout
    }
}