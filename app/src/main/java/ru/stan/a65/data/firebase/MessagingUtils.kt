package ru.stan.a65.data.firebase

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.stan.a65.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class MessagingUtils(
    private val msgInstance: FirebaseMessaging
) {
    fun logToken(tag: String = "FCM token") {
        msgInstance.token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(tag, task.result)
                return@addOnCompleteListener
            }

        }
    }

    inner class MyFirebaseMessagingService : FirebaseMessagingService() {
        override fun onMessageReceived(message: RemoteMessage) {
            super.onMessageReceived(message)
            val notification = NotificationCompat.Builder(this, "FCM_chanel")
                .setSmallIcon(R.drawable.harry)
                .setContentTitle(message.data["message"])
                .setContentText(message.data["message"] + convertToDate(message.data["timestamp"]))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                NotificationManagerCompat.from(this)
                    .notify(Random.nextInt(), notification)
            }

        }

        private fun convertToDate(timesTamp: String?): String? {
            timesTamp ?: return ""
            return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                .format(Date(timesTamp.toLong()))
        }


        override fun onNewToken(token: String) {
            super.onNewToken(token)
        }
    }


}
