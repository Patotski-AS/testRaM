package com.example.android.testram.presentation.ui.character_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.testram.domain.model.CharacterInfo
import com.example.android.testram.domain.usecases.GetRemoteCharacterUseCase
import com.example.android.testram.domain.utill.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
  private val getRemoteCharacterUseCase: GetRemoteCharacterUseCase
) : ViewModel() {

//    private var _characterInfo = MutableStateFlow(CharacterInfo())
//    val characterInfo: StateFlow<CharacterInfo> = _characterInfo.asStateFlow()
//
//    fun setCharacterInfo(characterInfo: CharacterInfo) {
//        _characterInfo.tryEmit(characterInfo)
//    }

    private var _characterInfo = MutableStateFlow<Resource<CharacterInfo>>(Resource.Empty)
    val characterInfo = _characterInfo.asStateFlow()

    fun getCharacter(id: String) {
        viewModelScope.launch {
            getRemoteCharacterUseCase.invoke(id).stateIn(viewModelScope).collect {
                _characterInfo.tryEmit(it)
            }
        }
    }


}