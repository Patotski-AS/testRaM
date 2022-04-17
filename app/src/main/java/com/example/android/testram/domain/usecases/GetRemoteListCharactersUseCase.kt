package com.example.android.testram.domain.usecases

import com.example.android.testram.domain.repository.CharacterRepository

class GetRemoteListCharactersUseCase(private val repository: CharacterRepository) {
    operator fun invoke() = repository.getListCharacters()
}
