package com.example.pokemonapp.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.example.pokemonapp.domain.model.Result

class ListPokemonsPagingAdapter(
    private val onClick: (id: Int) -> Unit
) :
    PagingDataAdapter<Result, ListPokemonsViewHolder>(ListPokemonsDiffUtil()) {

    override fun onBindViewHolder(holder: ListPokemonsViewHolder, position: Int) {
        getItem(position)?.let { pokemon ->
            holder.bind(pokemon)
            holder.itemView.setOnClickListener {
                onClick(position + 1)
            }
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