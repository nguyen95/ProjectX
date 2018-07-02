package com.example.trungnguyen.projectx.appLock

import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.util.Log.e
import com.example.trungnguyen.projectx.R

/**
 * Created by Trung Nguyen on 31-May-18.
 */
class LockService(): Service() {

    lateinit var receiver: LockReceiver
    override fun onBind(p0: Intent?): IBinder? = null

//    @SuppressLint("MissingPermission")
    override fun onCreate(){
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var iF = IntentFilter(Intent.ACTION_SCREEN_ON)
        iF.addAction(Intent.ACTION_SCREEN_OFF)
        iF.addAction(Intent.ACTION_BOOT_COMPLETED)

        Log.e("sending...","")
        receiver = LockReceiver()
        registerReceiver(receiver,iF)

        startForeground()
        return START_STICKY
    }

    private fun startForeground(){
        val notification = NotificationCompat.Builder(this)
                .setContentTitle(resources.getString(R.string.app_name))
                .setTicker(getResources().getString(R.string.app_name))
                .setContentText("Running")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(null)
                .setOngoing(true)
                .build();
        startForeground(9999,notification);
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

}