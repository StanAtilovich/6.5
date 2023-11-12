package ru.stan.a65

import android.app.Application
import androidx.work.Configuration
import ru.stan.a65.data.firebase.FirebaseUtils
import ru.stan.a65.data.local.database.CharacterDatabase
import ru.stan.a65.di.ContextModule
import ru.stan.a65.di.DaggerApplicationComponent
import ru.stan.a65.presentation.Utils.NotificationUtils
import ru.stan.a65.presentation.Utils.PermissionUtils


class App : Application(), Configuration.Provider {
    lateinit var db: CharacterDatabase
        private set

    lateinit var firebaseInstance: FirebaseUtils
        private set
    lateinit var notificationService: NotificationUtils
        private set
    lateinit var permissionService: PermissionUtils
        private set


 //   lateinit var repo: CharacterRepositoryImpl
    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
        permissionService = PermissionUtils.getInstance(this)
        //db
        db = CharacterDatabase.getInstance(INSTANCE)

        firebaseInstance =
            DaggerApplicationComponent.builder()
                .contextModule(ContextModule(this))
                .build()
                .firebaseUtils()

        //выключаем крашлитиксы
        firebaseInstance.crashlytics.setCrashlyticsCollectionEnabled(false)

        //сдесь включаем NotificationUtils
        notificationService = NotificationUtils.getInstance(this)
        notificationService.createNotificationChannel()


            // repo = CharacterRepositoryImpl(this, CharacterMapper(), db.characterDao())
    }


    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(
            DaggerApplicationComponent.builder()
                .contextModule(ContextModule(INSTANCE))
                .build()
                .casherViewModelModelFactory()
            )
            .build()
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

}