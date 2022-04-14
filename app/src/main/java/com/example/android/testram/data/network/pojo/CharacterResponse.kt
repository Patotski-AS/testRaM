package com.example.android.testram.data.network.pojo


import com.squareup.moshi.Json

data class CharacterResponse(
    @Json(name = "results") val results: List<CharacterPojo>
)