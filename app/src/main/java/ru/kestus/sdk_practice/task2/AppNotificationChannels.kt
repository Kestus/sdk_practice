package ru.kestus.sdk_practice.task2

import android.app.NotificationManager
import ru.kestus.sdk_practice.R

enum class AppNotificationChannels(
    val channelId: String,
    val channelName: String,
    val channelIcon: Int = R.drawable.ic_launcher_foreground,
    val importance: Int = NotificationManager.IMPORTANCE_DEFAULT,
) {
    CHARGING_STATUS("charging_channel_id", "Charging Status"),
}
