package com.example.trungnguyen.projectx.appLock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log.e

/**
 * Created by Trung Nguyen on 31-May-18.
 */
class LockReceiver: BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        var action = p1?.action
        if(action.equals(Intent.ACTION_SCREEN_OFF)||action.equals(Intent.ACTION_BOOT_COMPLETED)){
            e("received","")
            p0?.startActivity(
                    Intent(p0,LockActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

}