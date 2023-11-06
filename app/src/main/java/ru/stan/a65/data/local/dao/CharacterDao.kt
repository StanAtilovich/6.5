package ru.stan.a65.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.stan.a65.data.local.entity.CharacterDb

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    suspend fun getAll(): List<CharacterDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterDb: CharacterDb)

    @Update
    suspend fun update(characterDb: CharacterDb)

    @Delete
    suspend fun delete(characterDb: CharacterDb)

}