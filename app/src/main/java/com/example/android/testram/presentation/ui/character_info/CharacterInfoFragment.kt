package com.example.android.testram.presentation.ui.character_info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.testram.R
import com.example.android.testram.databinding.FragmentCharacterInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterInfoFragment : Fragment(R.layout.fragment_character_info) {
    private var binding: FragmentCharacterInfoBinding? = null
    private val viewModel: CharacterInfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharacterInfoBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}