package ru.stan.a65.presentation.Utils

import android.annotation.SuppressLint
import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ru.stan.a65.App
import ru.stan.a65.R
import ru.stan.a65.presentation.ui.Activities.MainActivity


class NotificationUtils(
    private val application: Application
) {
    fun createNotificationChanel() {
        if (checkNotificationMoreThenOreo()) {
            val name = "Our notificationChanel"
            val discreptionText = "Some description Text"
            val impotants = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, impotants).apply {
                description = discreptionText
                enableVibration(true)
            }

            val notificationManager =
                application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification() {
        val intent = Intent(application, MainActivity::class.java)
        val pendidngIntent = PendingIntent.getActivity(
            application, 0, intent, getCorrectFlagFoePendingIntent()
        )
        val notification = NotificationCompat.Builder(application, CHANNEL_ID)
            .setContentTitle("Проверка")
            .setContentText("Описание нотификации")
            .setSmallIcon(R.drawable.notification)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendidngIntent)
            .setAutoCancel(true)
            .build()

        val isGrantedNotification =
            (application as App).permissionService.isPostNotNotificationsGranted()
        if (isGrantedNotification != null) {
            if (isGrantedNotification){
                showNotification(notification)
            }
            else {
                showNotification(notification)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun showNotification(notification: Notification) {
        NotificationManagerCompat.from(application).notify(NOTIFICATINO_ID, notification)
    }


    private fun checkNotificationMoreThenOreo() =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O


    private fun getCorrectFlagFoePendingIntent(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
    }

    companion object {
        private const val CHANNEL_ID = "Chanel Id"
        private const val NOTIFICATINO_ID = 555
        private var INSTANCE: NotificationUtils? = null
        private val LOCK = Any()
        fun getInstance(application: Application): NotificationUtils {
            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                INSTANCE = NotificationUtils(application)
                return NotificationUtils(application)
            }
        }
    }
}
