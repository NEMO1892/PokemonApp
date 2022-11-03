package com.example.pokemonapp.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemLoadingStateBinding

class ListPokemonsLoadingAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ListPokemonsLoadingAdapter.LoadingStateViewHolder>() {

    class LoadingStateViewHolder(binding: ItemLoadingStateBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        private val tvErrorMessage: TextView = binding.errorMessageTextView
        private val progressBar: ProgressBar = binding.progressBar
        private val btnRetry: Button = binding.retryButton

        init {
            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bindState(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                tvErrorMessage.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder = LoadingStateViewHolder(
        ItemLoadingStateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), retry
    )
}
