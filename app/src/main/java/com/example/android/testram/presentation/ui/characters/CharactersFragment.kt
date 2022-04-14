package com.example.android.testram.presentation.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.testram.R
import com.example.android.testram.databinding.FragmentCharactersBinding

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private var binding: FragmentCharactersBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactersBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}