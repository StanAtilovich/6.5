package ru.stan.a65

import android.app.Application
import androidx.work.Configuration
import ru.stan.a65.data.firebase.FirebaseUtils
import ru.stan.a65.data.local.database.CharacterDatabase
import ru.stan.a65.data.mapper.CharacterMapper
import ru.stan.a65.data.repository.CharacterRepositoryImpl
import ru.stan.a65.di.ContextModule
import ru.stan.a65.di.DaggerApplicationComponent
import ru.stan.a65.domain.usecase.CashCharacterListUseCase
import ru.stan.a65.domain.usecase.UploadListUseCase
import ru.stan.a65.presentation.Utils.NotificationUtils
import ru.stan.a65.presentation.Utils.PermissionUtils
import ru.stan.a65.presentation.ui.fragmentWorkManager.CasherDataWorkerFactory
import ru.stan.a65.presentation.worker.CashingDataWorker


class App : Application(), Configuration.Provider {

    lateinit var permissionService: PermissionUtils
        private set
    lateinit var db: CharacterDatabase
        private set

    lateinit var firebaseInstance: FirebaseUtils
        private set

    lateinit var notificationService: NotificationUtils
        private set

    lateinit var repo: CharacterRepositoryImpl
    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

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
        permissionService = PermissionUtils.getInstance(this)

        repo = CharacterRepositoryImpl(this, CharacterMapper(), db.characterDao())
    }


    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(
                CasherDataWorkerFactory(
                    UploadListUseCase(repo),
                    CashCharacterListUseCase(repo)
                )
            )
            .build()
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

}