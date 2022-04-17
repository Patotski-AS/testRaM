package com.example.android.testram.domain.repository

import androidx.paging.PagingData
import com.example.android.testram.domain.model.CharacterInfo
import com.example.android.testram.domain.utill.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getListCharacters(): Flow<PagingData<CharacterInfo>>
    fun getCharacter(id: String):Flow<Resource<CharacterInfo>>
}
