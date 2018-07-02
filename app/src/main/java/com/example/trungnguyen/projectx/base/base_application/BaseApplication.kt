package com.example.trungnguyen.projectx.base.base_application

import android.app.Application
import android.support.annotation.NonNull
import android.util.Log
import com.example.trungnguyen.projectx.BuildConfig
import timber.log.Timber

/**
 * Created by Trung Nguyen on 09-Jun-18.
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        } else {
            Timber.plant(CrashReportingTree());
        }
    }

    private inner class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String, @NonNull message: String, t: Throwable) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}