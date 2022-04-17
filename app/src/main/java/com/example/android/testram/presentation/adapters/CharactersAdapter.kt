package com.example.android.testram.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.android.testram.databinding.ItemCharacterBinding
import com.example.android.testram.domain.model.CharacterInfo
import javax.inject.Inject

class CharactersAdapter @Inject constructor(
    private val glide: RequestManager
) : PagingDataAdapter<CharacterInfo, CharactersAdapter.CharacterViewHolder>(CharactersDiffCallback) {
    private var listener: AdaptersListener? = null

    fun setOnClickListener(onClickListener: AdaptersListener) {
        this.listener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(holder.bindingAdapterPosition))
    }

    private object CharactersDiffCallback : DiffUtil.ItemCallback<CharacterInfo>() {

        override fun areItemsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean {
            return oldItem == newItem
        }
    }

    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(characterInfo: CharacterInfo?) {
            val image = characterInfo?.image

                glide.load(image)
                    .fitCenter()
                    .into(binding.ivItemAvatar)

            binding.apply {
                characterInfo?.let { info ->
                    tvValueName.text = info.name
                    tvTitleGender.text = info.gender
                    tvValueSpecies.text = info.species
                }
            }
            initButtonsListeners(characterInfo)
        }

        private fun initButtonsListeners(characterInfo: CharacterInfo?) {
            binding.cardItem.setOnClickListener {
                characterInfo?.let { characterInfo -> listener?.onClickItem(characterInfo) }
            }
        }
    }
}