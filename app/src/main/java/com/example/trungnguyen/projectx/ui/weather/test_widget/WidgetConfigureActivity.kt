package com.example.trungnguyen.projectx.ui.weather.test_widget

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import com.example.trungnguyen.projectx.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.widget_configure.*
import net.aksingh.owmjapis.core.OWM
import timber.log.Timber
import timber.log.Timber.e

/**
 * Created by Trung Nguyen on 11-Jun-18.
 */
class WidgetConfigureActivity : Activity() {

    var mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    companion object {
        private val PREFS_NAME = "AppWidget"
        private val PREF_PREFIX_KEY = "appwidget"

        fun deleteTitlePref(context: Context, appWidgetId: Int) {
            var prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
            prefs.remove(PREF_PREFIX_KEY + appWidgetId);
            prefs.apply();
        }

        // Write the prefix to the SharedPreferences object for this widget
        fun saveTitlePref(context: Context, appWidgetId: Int, text: String) {
            var prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
            prefs.putString(PREF_PREFIX_KEY + appWidgetId, text)
            prefs.apply()
        }

        // Read the prefix from the SharedPreferences object for this widget.
        // If there is no preference saved, get the default from a resource
        fun loadTitlePref(context: Context, appWidgetId: Int): String {
            var prefs = context.getSharedPreferences(PREFS_NAME, 0)
            var titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null)
            return titleValue ?: "aaaaaaa"
        }

        fun loadTitlePref2(context: Context, appWidgetId: Int): String {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0)
            var titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null)
            return if (titleValue != null) titleValue else "aaaaaaaaa"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResult(Activity.RESULT_CANCELED)
        setContentView(R.layout.widget_configure)

        e("aaaaa")
//        this.setFinishOnTouchOutside(true)

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

//        var widgetText = appwidget_text.getText().toString()
//        createWidget(this, widgetText)

        add_button.setOnClickListener { view ->

            // When the button is clicked, get text
            var widgetText = appwidget_text.getText().toString()
            createWidget(this, widgetText)
        }

        // Defined array values to show in ListView
        var values = arrayOf("Don't forget the milk!",
                "Do not forget to go get the mother-in-law",
                "Go to the laundry",
                "Marise number 0123456789")

        var adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values)

        list.setAdapter(adapter)

        // ListView Item Click Listener
        list.setOnItemClickListener { adapterView, view, i, l ->

        }

        // Find the widget id from the intent.
        var intent = getIntent()
        var extras = intent.getExtras()
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            e("finish")
            finish()
            return
        }

        appwidget_text.setText(loadTitlePref(this, mAppWidgetId))
    }

    fun createWidget(context: Context, widgetText: String) {
        // Store the string locally
        saveTitlePref(context, mAppWidgetId, widgetText)

        // It is the responsibility of the configuration activity to update the app widget
        var appWidgetManager = AppWidgetManager.getInstance(context)
        AppWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId)

        // Make sure we pass back the original appWidgetId
        var resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId)
        setResult(RESULT_OK, resultValue)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask()
        } else {
            finish()
        }
    }
}