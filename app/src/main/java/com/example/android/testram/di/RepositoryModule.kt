package com.example.android.testram.di

import android.content.Context
import com.example.android.testram.data.mappers.MapCharacterPojoToCharacterInfo
import com.example.android.testram.data.network.TestRaMApiService
import com.example.android.testram.data.repository.CharacterRepositoryImpl
import com.example.android.testram.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(
        apiService: TestRaMApiService,
        mapCharacterPojoToCharacterInfo: MapCharacterPojoToCharacterInfo,
        @ApplicationContext context: Context
    ): CharacterRepository {
        return CharacterRepositoryImpl(
            service = apiService,
            mapCharacterPojoToCharacterInfo = mapCharacterPojoToCharacterInfo,
            context=context
        )
    }
}