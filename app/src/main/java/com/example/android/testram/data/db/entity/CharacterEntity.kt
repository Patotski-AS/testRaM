package com.example.android.testram.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_db") val idDb: Int = 0,
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String,
    val episode: List<String>,
    val url: String,
    @ColumnInfo(name = "prev_key") var prevKey: Int?,
    @ColumnInfo(name = "next_key") var nextKey: Int?
    )