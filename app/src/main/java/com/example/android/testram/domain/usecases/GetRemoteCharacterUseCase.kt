package com.example.android.testram.domain.usecases

import com.example.android.testram.domain.repository.CharacterRepository

class GetRemoteCharacterUseCase(private val repository: CharacterRepository) {
    operator fun invoke(id: String) = repository.getCharacter(id)
}
