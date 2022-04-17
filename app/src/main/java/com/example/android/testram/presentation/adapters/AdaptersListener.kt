package com.example.android.testram.presentation.adapters

import com.example.android.testram.domain.model.CharacterInfo

interface AdaptersListener {
    fun onClickItem(characterInfo: CharacterInfo)
}
