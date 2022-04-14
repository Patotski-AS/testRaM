package com.example.android.testram.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.testram.data.db.entity.CharacterEntity

@Dao
interface CharacterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: List<CharacterEntity>)

    @Query("SELECT * from characters WHERE id = :key")
    suspend fun getCharacterById(key: Long): CharacterEntity?

    @Query("DELETE FROM characters")
    suspend fun clear()

    @Query("SELECT * FROM characters ")
    fun getAllCharacters(): List<CharacterEntity>

}