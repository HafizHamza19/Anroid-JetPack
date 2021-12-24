package com.bmcsolution.jetpacklearning.Architecture.workManager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bmcsolution.jetpacklearning.R
import kotlin.random.Random

class NotificationWorker(private val context: Context, private val params: WorkerParameters):Worker(context, params) {
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private val channelId = "12345"
    private val description = "Test Notification"
    override fun doWork(): Result {

        Log.d("Tag", "NoftiFy")
        val randomId=(Math.random()*1000).toInt()
        sendNotification("hello","hi",randomId)
        return Result.success()
    }

    //This method is only generating push notification
    private fun sendNotification(title: String, messageBody: String, diffId: Int) {
        val CHANNEL_ID = "my_channel_01"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.bg_card)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(Notification.PRIORITY_HIGH)
                .setChannelId(CHANNEL_ID)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
        notificationBuilder.priority = NotificationCompat.PRIORITY_MAX
        notificationBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, CHANNEL_ID, importance)
            mChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(mChannel)
        }
        notificationManager.notify(diffId, notificationBuilder.build())
    }

}