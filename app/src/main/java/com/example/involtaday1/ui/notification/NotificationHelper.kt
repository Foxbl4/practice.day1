package com.example.involtaday1.ui.notification


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.involtaday1.R


object NotificationHelper {

    private const val notifyFragmentChannelId = "Test Notify Chanel"
    private const val notifyFragmentId = 1
    private val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

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
            .setContentTitle("This is Notification!")
            .setSmallIcon(R.drawable.ic_baseline_image_search_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(msg)
            )

        val uri = Uri.parse("android.resource://"
                + context.packageName + "/" + R.raw.mysound)
        val mRingtone = RingtoneManager.getRingtone(
            context.applicationContext,
            uri
        )
        mRingtone.play()

        with(NotificationManagerCompat.from(context)) {
            notify(notifyFragmentId, builder.build())
        }
    }
}