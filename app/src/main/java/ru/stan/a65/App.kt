package ru.stan.a65

import android.app.Application
import androidx.room.Room
import com.google.firebase.crashlytics.FirebaseCrashlytics
import ru.stan.a65.data.local.database.CharacterDatabase


class App : Application() {

    lateinit var db: CharacterDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(
            true
        )

        INSTANCE = this

        db = Room
            .databaseBuilder(
                this,
                CharacterDatabase::class.java,
                "db"
            )
//            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

}