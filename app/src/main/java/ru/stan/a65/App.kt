package ru.stan.a65

import android.app.Application
import ru.stan.a65.data.firebase.FirebaseUtils
import ru.stan.a65.data.local.database.CharacterDatabase
import ru.stan.a65.presentation.NotificationUtils
import ru.stan.a65.presentation.PermissionUtils


class App : Application() {

    lateinit var permissionService: PermissionUtils
        private set
    lateinit var db: CharacterDatabase
        private set

    lateinit var firebaseInstance: FirebaseUtils
        private set

    lateinit var notificationService: NotificationUtils
        private set

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        //db
        db = CharacterDatabase.getInstance(INSTANCE)

        firebaseInstance = FirebaseUtils.getInstance(this)

        //выключаем крашлитиксы
        firebaseInstance.crashlytics.setCrashlyticsCollectionEnabled(false)

        //сдесь включаем NotificationUtils
        notificationService = NotificationUtils.getInstance(this)
        notificationService.createNotificationChanel()
        permissionService= PermissionUtils.getInstance(this)

    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

}