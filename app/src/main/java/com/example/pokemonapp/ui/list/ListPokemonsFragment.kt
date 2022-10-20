package com.example.pokemonapp.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentListPokemonsBinding
import com.example.pokemonapp.di.MyApplication
import com.example.pokemonapp.model.Result
import com.example.pokemonapp.ui.list.adapter.ListPokemonsLoadingAdapter
import com.example.pokemonapp.ui.list.adapter.ListPokemonsPagingAdapter
import com.example.pokemonapp.ui.pokemon.ID_POKEMON
import com.example.pokemonapp.ui.pokemon.PokemonFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListPokemonsFragment : Fragment() {

    private var binding: FragmentListPokemonsBinding? = null

    @Inject
    lateinit var viewModelProvider: ListPokemonsViewModelProvider

    private lateinit var viewModel: ListPokemonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListPokemonsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelProvider)[ListPokemonsViewModel::class.java]
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if (enter) {
            AnimationUtils.loadAnimation(context, R.anim.from_top)
        } else {
            AnimationUtils.loadAnimation(context, R.anim.to_bottom)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        binding?.run {
            lifecycleScope.launch {
                viewModel.flow.collectLatest {
                    initAdapter(it)
                }
            }
        }
    }

    private fun setupViews() {
        binding?.run {
            (recyclerView.adapter as? ListPokemonsPagingAdapter)?.withLoadStateHeaderAndFooter(
                header = ListPokemonsLoadingAdapter { (recyclerView.adapter as? ListPokemonsPagingAdapter)?.retry() },
                footer = ListPokemonsLoadingAdapter { (recyclerView.adapter as? ListPokemonsPagingAdapter)?.retry() }
            )
        }
    }

    private suspend fun initAdapter(list: PagingData<Result>) {
        binding?.run {
            if (recyclerView.adapter == null) {
                recyclerView.adapter = ListPokemonsPagingAdapter { id: Int ->
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, PokemonFragment().apply {
                            arguments = bundleOf(
                                ID_POKEMON to id
                            )
                        })
                        .addToBackStack("")
                        .commit()
                }
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
            (recyclerView.adapter as? ListPokemonsPagingAdapter)?.submitData(list)
        }
    }
}
