package com.example.pokemonapp.ui.pokemon

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonBinding
import com.example.pokemonapp.di.MyApplication
import com.example.pokemonapp.util.loadImage
import javax.inject.Inject

const val ID_POKEMON = "id_pokemon"

class PokemonFragment : Fragment() {

    private var binding: FragmentPokemonBinding? = null

    private lateinit var viewModel: PokemonViewModel

    @Inject
    lateinit var viewModelProvider: PokemonViewModelProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelProvider)[PokemonViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.run {
            viewModel.run {
                onePokemon.observe(viewLifecycleOwner) { pokemon ->
                    imagePokemonImageView.loadImage(pokemon.sprites.front_default)
                    nameTextView.text = pokemon.name
                    pokemon.types.forEach {
                        typeTextView.append("${it.type.name} \n")
                    }
                    weightTextView.text = "${(pokemon.weight / 10)} ${getString(R.string.kg)}"
                    heightTextView.text = "${(pokemon.height * 10)} ${getString(R.string.cm)}"
                }
            }
            arguments?.getInt(ID_POKEMON)?.let { id ->
                viewModel.getOnePokemon(id)
            }
            floatingBackButton.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }
}