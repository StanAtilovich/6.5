package ru.stan.a65

import android.app.Application
import androidx.work.Configuration
import ru.stan.a65.data.firebase.FirebaseUtils
import ru.stan.a65.data.local.database.CharacterDatabase
import ru.stan.a65.di.ApplicationComponent
import ru.stan.a65.di.DaggerApplicationComponent
import ru.stan.a65.presentation.Utils.NotificationUtils
import ru.stan.a65.presentation.Utils.PermissionUtils
import javax.inject.Inject


class App : Application(), Configuration.Provider {
    lateinit var db: CharacterDatabase
        private set

    @Inject
    lateinit var appComponent: ApplicationComponent

    @Inject
    lateinit var firebaseInstance: FirebaseUtils
    lateinit var notificationService: NotificationUtils
        private set
    lateinit var permissionService: PermissionUtils
        private set


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        DaggerApplicationComponent.factory().create(this).inject(this)

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
                appComponent.casherViewModelModelFactory()
            )
            .build()
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

}