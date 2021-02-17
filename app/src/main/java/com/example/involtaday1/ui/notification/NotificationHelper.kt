package com.example.involtaday1.ui.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.involtaday1.R

object NotificationHelper {

    private const val notifyFragmentChannelId = "Test Notify Chanel"
    private const val notifyFragmentId = 1

    private fun createNotificationChannel(
        context: Context,
        notifyName: String,
        descriptionText: String,
        channelId: String
    ) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, notifyName, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showNotificationWithText(msg: String, context: Context) {
        val name = context.getString(R.string.test_notify_name)
        val desc = context.getString(R.string.test_notify_desc)

        createNotificationChannel(context, name, desc, notifyFragmentChannelId)

        val builder = NotificationCompat.Builder(context, notifyFragmentChannelId)
            .setContentText(msg)
            .setSmallIcon(R.drawable.image_1)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(notifyFragmentId, builder.build())
        }
    }
}