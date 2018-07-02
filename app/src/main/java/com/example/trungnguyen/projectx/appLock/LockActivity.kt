package com.example.trungnguyen.projectx.appLock

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.trungnguyen.projectx.R
import kotlinx.android.synthetic.main.layout_lock.*
import timber.log.Timber.e

/**
 * Created by Trung Nguyen on 31-May-18.
 */
class LockActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onAttachedToWindow()
        super.onCreate(savedInstanceState)

        makeFullScreen()
        startService(Intent(this, LockService::class.java))

        setContentView(R.layout.layout_lock)

        var homeWatcher = HomeWatcher(this)
        homeWatcher.setOnHomePressedListener(object : OnHomePressedListener {
            override fun onHomePressed() {
                e("HOMEEEEE Press")
//                startActivity(Intent(applicationContext,LockActivity::class.java)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NEW_TASK))
                return
            }

            override fun onHomeLongPressed() {
                e("HOMEEEEE Long Press")
                return
            }

        })
        homeWatcher.startWatch()

        edt_pass.setOnClickListener({
            //            view -> edt_pass.isFocusable = true
        })

        edt_pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkPass(edt_pass.text.toString())
            }
        })

    }

    fun checkPass(toString: String) {
        if (toString.equals("1234")) {
            unlockScreen()
        } else {
            Toast.makeText(this, "invalid password", Toast.LENGTH_SHORT).show()
        }
    }

    fun makeFullScreen() {
        var flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        this.window.decorView.systemUiVisibility = flags
    }

    fun unlockScreen() {
        e("unlock")
        android.os.Process.killProcess(android.os.Process.myPid())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity()
        }
        super.finish()
    }

    override fun onBackPressed() {
        return
    }

    override fun onPause() {
        super.onPause()
        var activMana = applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activMana.moveTaskToFront(taskId, 0)
    }

    override fun onStop() {
        super.onStop()
    }

}