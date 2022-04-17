package com.example.android.testram.presentation.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.android.testram.R
import com.example.android.testram.databinding.FragmentCharactersBinding
import com.example.android.testram.domain.model.CharacterInfo
import com.example.android.testram.presentation.adapters.AdaptersListener
import com.example.android.testram.presentation.adapters.CharactersAdapter
import com.example.android.testram.presentation.adapters.CharactersLoadStateAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment(), AdaptersListener {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels()

    @Inject
    lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charactersAdapter.setOnClickListener(this)

        with(binding) {
            rvCharacters.adapter = charactersAdapter.withLoadStateFooter(
                footer = CharactersLoadStateAdapter { charactersAdapter.retry() }
            )
            swipeRefresh.setOnRefreshListener {
                charactersAdapter.retry()
                swipeRefresh.isRefreshing = false
            }
        }

        lifecycleScope.launch {
            viewModel.characters.collectLatest(charactersAdapter::submitData)
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            charactersAdapter.loadStateFlow.collectLatest { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && charactersAdapter.itemCount == 0
                binding.rvCharacters.isVisible = !isListEmpty
                binding.tvErrorMessage.isVisible = charactersAdapter.itemCount == 0
                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Snackbar.make(
                        binding.root,
                        requireContext().getString(R.string.message_internet_connecton_error),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickItem(characterInfo: CharacterInfo) {
        val controller = findNavController()
        val currentDestination =
            (controller.currentDestination as? FragmentNavigator.Destination)?.className
                ?: (controller.currentDestination as? DialogFragmentNavigator.Destination)?.className
        if (currentDestination == this.javaClass.name) {
            controller.navigate(
                CharactersFragmentDirections.actionCharactersFragmentToCharacterInfoFragment(
                    characterInfo
                )
            )
        }
    }

}