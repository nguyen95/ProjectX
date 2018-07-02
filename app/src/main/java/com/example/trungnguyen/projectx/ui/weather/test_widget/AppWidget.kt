package com.example.trungnguyen.projectx.ui.weather.test_widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.example.trungnguyen.projectx.R

/**
 * Created by Trung Nguyen on 11-Jun-18.
 */
class AppWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
        for (appWidgetId in appWidgetIds) {
            WidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int){
            var widgetText = WidgetConfigureActivity.loadTitlePref(context, appWidgetId);
            // Construct the RemoteViews object
            var views = RemoteViews(context.getPackageName(), R.layout.widget);
            views.setTextViewText(R.id.appwidget_text, widgetText);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}