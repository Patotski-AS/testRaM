package com.example.android.testram.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterInfo(
    val id: Int = -1,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: String = "",
    val location: String = "",
    val image: String = "",
    val episode: List<String> = emptyList(),
    val url: String = ""
) : Parcelable