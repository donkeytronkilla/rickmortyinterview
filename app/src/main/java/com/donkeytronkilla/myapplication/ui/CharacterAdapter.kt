package com.donkeytronkilla.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.donkeytronkilla.myapplication.databinding.ItemCharacterBinding
import com.donkeytronkilla.myapplication.model.Character

class CharacterViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.apply {
            txtId.text = character.id.toString()
            Glide.with(itemView).load(character.image).into(imgCharacter)
        }
    }
    companion object {
        fun create(parent: ViewGroup) : CharacterViewHolder {
            val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CharacterViewHolder(binding)
        }
    }
}

private object Comparison: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}
class CharacterAdapter: PagingDataAdapter<Character, CharacterViewHolder>(Comparison) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)

        character?.let {
            holder.bind(character)
        }
    }
}