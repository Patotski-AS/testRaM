package com.example.android.testram.di

import com.example.android.testram.domain.repository.CharacterRepository
import com.example.android.testram.domain.usecases.GetRemoteCharacterUseCase
import com.example.android.testram.domain.usecases.GetRemoteListCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetRemoteListCharactersUseCase(
        repository: CharacterRepository
    ): GetRemoteListCharactersUseCase {
        return GetRemoteListCharactersUseCase(repository = repository)
    }

    @Provides
    fun provideGetRemoteCharacterUseCase(
        repository: CharacterRepository
    ): GetRemoteCharacterUseCase {
        return GetRemoteCharacterUseCase(repository = repository)
    }
}