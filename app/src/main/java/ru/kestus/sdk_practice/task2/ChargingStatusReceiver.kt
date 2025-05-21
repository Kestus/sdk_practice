package ru.kestus.sdk_practice.task2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ChargingStatusReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            AppNotificationManager(it).notify(
                AppNotificationChannels.CHARGING_STATUS,
                "Charging Started!"
            )
        } ?: throw RuntimeException("context == null")
    }

}
