package com.example.trungnguyen.projectx.base.baseALertDialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import com.example.trungnguyen.projectx.R

/**
 * Created by Trung Nguyen on 18-May-18.
 */
class LoadingDialog {
    companion object {
        private var mAlertDialog: AlertDialog? = null

        fun show(context: Context){
            if (mAlertDialog == null && context != null) {
                val loadDialog = LoadDialog(context)
                mAlertDialog = loadDialog.show()
            }
        }

        fun hide() {
            if (mAlertDialog != null) {
                if (mAlertDialog!!.isShowing()) {

                    //get the Context object that was used to great the dialog
                    val context = (mAlertDialog!!.getContext() as ContextWrapper).baseContext

                    // if the Context used here was an activity AND it hasn't been finished or destroyed
                    // then dismiss it
                    if (context is Activity) {

                        // Api >=17
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            if (!context.isFinishing && !context.isDestroyed) {
                                dismissWithExceptionHandling()
                            }
                        } else {

                            // Api < 17. Unfortunately cannot check for isDestroyed()
                            if (!context.isFinishing) {
                                dismissWithExceptionHandling()
                            }
                        }
                    } else
                    // if the Context used wasn't an Activity, then dismiss it too
                        dismissWithExceptionHandling()
                }
                mAlertDialog = null
            }
        }

        private fun dismissWithExceptionHandling() {
            try {
                mAlertDialog!!.dismiss()
            } catch (e: IllegalArgumentException) {
                // Do nothing.
            } catch (e: Exception) {
                // Do nothing.
            } finally {
                mAlertDialog = null
            }
        }
    }
}