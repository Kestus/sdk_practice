package ru.kestus.sdk_practice.task2

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class AppNotificationManager(private val context: Context) {

    private val notificationManager by lazy {
        NotificationManagerCompat.from(context) ?: throw NullPointerException()
    }

    init {
        // create channels, doesn't do anything if was created already.
        AppNotificationChannels.entries.forEach { channel ->
            createChannel(channel)
        }
    }

    private fun createChannel(channel: AppNotificationChannels) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    channel.channelId,
                    channel.channelName,
                    channel.importance
                )
            )
        }
    }

    fun createNotification(
        channel: AppNotificationChannels,
        title: String,
        text: String? = null,
    ): Notification =
        NotificationCompat.Builder(context, channel.channelId)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(channel.channelIcon)
            .build()

    fun notify(
        channel: AppNotificationChannels,
        title: String,
        text: String? = null,
    ) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            throw RuntimeException("POST_NOTIFICATIONS permission missing")
        }
        val notification = createNotification(channel, title, text)
        notificationManager.notify(AppNotificationChannels.CHARGING_STATUS.ordinal, notification)
    }
}