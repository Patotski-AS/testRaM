package com.example.android.testram.presentation.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.android.testram.domain.model.CharacterInfo
import com.example.android.testram.domain.usecases.GetRemoteListCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getRemoteListCharactersUseCase: GetRemoteListCharactersUseCase
) : ViewModel() {

    private val _characters = getRemoteListCharactersUseCase.invoke()
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    val characters: StateFlow<PagingData<CharacterInfo>>
        get() = _characters
}
