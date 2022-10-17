package com.example.pokemonapp.ui.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pokemonapp.model.Result

class ListPokemonsDiffUtil : DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}