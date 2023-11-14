package ru.stan.a65

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import ru.stan.a65.data.firebase.FirebaseUtils
import ru.stan.a65.data.local.database.CharacterDatabase
import ru.stan.a65.presentation.Utils.NotificationUtils
import ru.stan.a65.presentation.Utils.PermissionUtils
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {
    lateinit var db: CharacterDatabase
        private set

    @Inject
    lateinit var firebaseInstance: FirebaseUtils
    lateinit var notificationService: NotificationUtils
        private set
    lateinit var permissionService: PermissionUtils
        private set

    @Inject
    lateinit var CasherDataWorkerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        permissionService = PermissionUtils.getInstance(this)

        db = CharacterDatabase.getInstance(this)


        firebaseInstance.crashlytics.setCrashlyticsCollectionEnabled(false)


        notificationService = NotificationUtils.getInstance(this)
        notificationService.createNotificationChannel()
    }


    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(
                CasherDataWorkerFactory
            )
            .build()
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    init {
        INSTANCE = this
    }

}