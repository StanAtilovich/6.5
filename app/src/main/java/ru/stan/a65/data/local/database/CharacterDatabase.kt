package ru.stan.a65.data.local.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.stan.a65.data.local.entity.CharacterDbModel
import ru.stan.a65.data.local.dao.CharacterDao

@Database(entities = [CharacterDbModel::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {

        private var INSTANCE: CharacterDatabase? = null
        private const val DB_NAME = "character.db"
        private val LOCK_ODJECT = Any()
        fun getInstance(application: Application): CharacterDatabase {
            INSTANCE?.let { db ->
                return db
            }

            synchronized(LOCK_ODJECT) {
                INSTANCE?.let { db ->
                    return db
                }
                val db = Room
                    .databaseBuilder(
                        application,
                        CharacterDatabase::class.java,
                        DB_NAME
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}