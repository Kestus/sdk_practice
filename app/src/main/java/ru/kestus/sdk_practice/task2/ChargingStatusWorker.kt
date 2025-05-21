package ru.kestus.sdk_practice.task2

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters

class ChargingStatusWorker(
    private val context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
        }
        context.registerReceiver(ChargingStatusReceiver(), intentFilter)
        return Result.success()
    }

    companion object {
        fun makeRequest() = OneTimeWorkRequestBuilder<ChargingStatusWorker>().build()
    }


}