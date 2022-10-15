package com.example.pokemonapp.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.databinding.FragmentListPokemonsBinding
import com.example.pokemonapp.di.MyApplication
import com.example.pokemonapp.model.Result
import com.example.pokemonapp.ui.list.adapter.ListPokemonsAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            viewModel.run {
                listPokemons.observe(viewLifecycleOwner) {
                    initAdapter(it)
                }
                getListPokemons()
            }
        }
    }

    private fun initAdapter(list: ArrayList<Result>) {
        binding?.run {
            if (recyclerView.adapter == null) {
                recyclerView.adapter = ListPokemonsAdapter()
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
            (recyclerView.adapter as? ListPokemonsAdapter)?.setList(list)
        }
    }
}
