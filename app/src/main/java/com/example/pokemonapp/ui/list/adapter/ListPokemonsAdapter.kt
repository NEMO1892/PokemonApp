package com.example.pokemonapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.example.pokemonapp.model.Result

class ListPokemonsAdapter : RecyclerView.Adapter<ListPokemonsViewHolder>() {

    private var list: ArrayList<Result> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokemonsViewHolder =
        ListPokemonsViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListPokemonsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: ArrayList<Result>) {
        this.list = list
        notifyDataSetChanged()
    }
}