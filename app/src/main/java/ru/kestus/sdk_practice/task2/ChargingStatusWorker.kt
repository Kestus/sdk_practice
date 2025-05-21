package ru.kestus.sdk_practice.task2

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters

class ChargingStatusWorker(
    private val context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        AppNotificationManager(context).notify(
            AppNotificationChannels.CHARGING_STATUS,
            "Charging Started!"
        )
        return Result.success()
    }

    companion object {
        fun makeRequest() = OneTimeWorkRequestBuilder<ChargingStatusWorker>().apply {
            setConstraints(makeConstraints())
        }.build()

        private fun makeConstraints(): Constraints {
            return Constraints.Builder()
                .setRequiresCharging(true)
                .build()
        }
    }


}