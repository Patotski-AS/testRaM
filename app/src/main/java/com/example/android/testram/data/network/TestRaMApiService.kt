package com.example.android.testram.data.network;

import com.example.android.testram.data.network.pojo.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

private const val PARAMS_PAGE = "page"

interface TestRaMApiService {

    @GET("/api/character")
    suspend fun getCharacters(
        @retrofit2.http.Query(PARAMS_PAGE) page: String
    ): Response<CharacterResponse>
}
