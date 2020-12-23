package org.thebus.foreground_service

import android.app.IntentService
import android.content.Intent

/**
 * Created by ancamihesan on 12/23/20. Email anca.mihesan@rodeapps.com
 *
 */
class TestForegroundService : IntentService("org.thebus.foreground_service.TestForegroundService") {
    override fun onHandleIntent(intent: Intent?) {
        val notificationHelper = ForegroundServicePlugin.NotificationHelper();
        startForeground(notificationHelper.notificationId, notificationHelper.currentNotification)
    }
}
