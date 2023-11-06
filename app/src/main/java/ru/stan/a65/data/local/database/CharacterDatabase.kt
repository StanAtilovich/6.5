package ru.stan.a65.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.stan.a65.data.local.dao.CharacterDao
import ru.stan.a65.data.local.entity.CharacterDb

@Database(entities = [CharacterDb::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}