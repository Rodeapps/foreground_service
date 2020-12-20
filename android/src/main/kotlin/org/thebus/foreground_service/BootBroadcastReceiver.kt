package org.thebus.foreground_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

/**
 * Created by ancamihesan on 12/20/20. Email anca.mihesan@rodeapps.com
 *
 */

class BootBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        ForegroundServicePlugin.logDebug("received action" + intent.action)

        if (intent.action == Intent.ACTION_BOOT_COMPLETED && getServiceState(context) == ServiceState.STARTED) {
            try {
                val startServiceIntent = Intent(context, ForegroundServicePlugin::class.java)
                startServiceIntent.action = ForegroundServicePlugin.INTENT_ACTION_START_SERVICE
                //starting with O, have to startForegroundService instead of just startService
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(startServiceIntent)
                } else {
                    context.startService(startServiceIntent)
                }
            } catch (e: Exception) {
                ForegroundServicePlugin.logError("unexpected ${e::class} caught while launching service: ${e.message}")
            }
        }
    }
}