package com.example.pokemonapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.example.pokemonapp.model.Result

class ListPokemonsPagingAdapter :
    PagingDataAdapter<Result, ListPokemonsViewHolder>(ListPokemonsDiffUtil()) {

    override fun onBindViewHolder(holder: ListPokemonsViewHolder, position: Int) {
        getItem(position)?.let { pokemon ->
            holder.bind(pokemon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokemonsViewHolder =
        ListPokemonsViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}