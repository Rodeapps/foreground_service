package org.thebus.foreground_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

/**
 * Created by VladBonta on 10/13/20. Email dan.patacean@rodeapps.com
 *
 */

class StartReceiver : BroadcastReceiver() {
    init {
        print("StartReceiver init");

    }
    override fun onReceive(context: Context, intent: Intent) {
        print("INTENT received on StartReceiver");
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
//                && getServiceState(context) == ServiceState.STARTED
            Intent(context, ForegroundServicePlugin::class.java).also {
//                it.action = Actions.START.name
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    print("Starting the service in >=26 Mode from a BroadcastReceiver")
                    context.startForegroundService(it)
                    return
                }
                print("Starting the service in < 26 Mode from a BroadcastReceiver")
                context.startService(it)
            }
        }
    }
}