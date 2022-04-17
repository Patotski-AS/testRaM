package com.example.android.testram.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.testram.databinding.ItemErrorBinding

class CharactersLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CharactersLoadStateAdapter.CharactersLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: CharactersLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharactersLoadStateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemErrorBinding.inflate(layoutInflater, parent, false)
        return CharactersLoadStateViewHolder(binding)
    }

    inner class CharactersLoadStateViewHolder(
        private val binding: ItemErrorBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState == LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
            }
            initButtonsListeners()
        }

        private fun initButtonsListeners() {
            binding.retryButton.setOnClickListener {
                retry.invoke()
            }
        }
    }
}


