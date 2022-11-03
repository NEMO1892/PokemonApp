package com.example.pokemonapp.presentation.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.example.pokemonapp.domain.model.Result

class ListPokemonsViewHolder(
    private val binding: ItemPokemonBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Result) {
        binding.run {
            nameTextView.text = item.name
        }
    }
}