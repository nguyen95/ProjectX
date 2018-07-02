package com.example.trungnguyen.projectx.appLock

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.util.Log


/**
 * Created by Trung Nguyen on 06-Jun-18.
 */
class HomeWatcher {
    val TAG = "hg"
    private lateinit var mContext: Context
    private lateinit var mFilter: IntentFilter
    private lateinit var mListener: OnHomePressedListener
    private lateinit var mRecevier: InnerRecevier

    constructor(context: Context) {
        mContext = context
        mFilter = IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
    }

    fun setOnHomePressedListener(listener: OnHomePressedListener) {
        mListener = listener
        mRecevier = InnerRecevier()
    }

    fun startWatch() {
        if (mRecevier != null) {
            mContext.registerReceiver(mRecevier, mFilter)
        }
    }

    fun stopWatch() {
        if (mRecevier != null) {
            mContext.unregisterReceiver(mRecevier)
        }
    }

    internal inner class InnerRecevier : BroadcastReceiver() {
        val SYSTEM_DIALOG_REASON_KEY = "reason"
        val SYSTEM_DIALOG_REASON_GLOBAL_ACTIONS = "globalactions"
        val SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps"
        val SYSTEM_DIALOG_REASON_HOME_KEY = "homekey"

        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == Intent.ACTION_CLOSE_SYSTEM_DIALOGS) {
                val reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY)
                if (reason != null) {
                    Log.e(TAG, "action:$action,reason:$reason")
                    if (mListener != null) {
                        if (reason == SYSTEM_DIALOG_REASON_HOME_KEY) {
                            mListener!!.onHomePressed()
                        } else if (reason == SYSTEM_DIALOG_REASON_RECENT_APPS) {
                            mListener!!.onHomeLongPressed()
                        }
                    }
                }
            }
        }
    }

}

interface OnHomePressedListener {
    fun onHomePressed()
    fun onHomeLongPressed()
}