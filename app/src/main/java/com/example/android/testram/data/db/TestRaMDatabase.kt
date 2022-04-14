package com.example.android.testram.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.testram.data.db.dao.CharacterDAO
import com.example.android.testram.data.db.entity.CharacterEntity
import com.example.android.testram.data.db.utill.Converters

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TestRaMDatabase : RoomDatabase() {
    abstract fun characterDAO(): CharacterDAO
}
