package com.example.trungnguyen.projectx.base.baseALertDialog

import android.app.AlertDialog
import android.content.Context
import android.support.annotation.NonNull
import android.view.LayoutInflater

/**
 * Created by Trung Nguyen on 18-May-18.
 */
abstract class BaseAlertDialog : AlertDialog.Builder {

    constructor(@NonNull context: Context) : super(context) {
        var view = LayoutInflater.from(context).inflate(setContentView(), null, false)
        setView(view)
        initVariable()
        initData()
        setCancelable(isCancel())
    }

    internal abstract fun initVariable()

    internal abstract fun isCancel(): Boolean

    internal abstract fun initData()

    internal abstract fun setContentView(): Int

    abstract var alertDialog: AlertDialog?

    override fun show(): AlertDialog {
        alertDialog = this.create()
//        alertDialog!!.window.decorView.setBackgroundResource(android.R.color.transparent)
        alertDialog!!.show()
        return alertDialog!!
    }

    fun dismiss() {
        alertDialog!!.dismiss()
    }
}