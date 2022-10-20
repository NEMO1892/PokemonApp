package com.example.pokemonapp.ui.pokemon

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonBinding
import com.example.pokemonapp.di.MyApplication
import com.example.pokemonapp.model.PokemonLoadingState
import com.example.pokemonapp.util.ConnectivityLiveData
import com.example.pokemonapp.util.loadImage
import javax.inject.Inject

const val ID_POKEMON = "id_pokemon"

class PokemonFragment : Fragment() {

    private var binding: FragmentPokemonBinding? = null

    private lateinit var viewModel: PokemonViewModel

    @Inject
    lateinit var connectivityLiveData: ConnectivityLiveData

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

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if (enter) {
            AnimationUtils.loadAnimation(context, R.anim.from_top)
        } else {
            AnimationUtils.loadAnimation(context, R.anim.to_bottom)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseObservers()
        startShimmerAnimation()
        backButton()
    }

    private fun backButton() {
        binding?.floatingBackButton?.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initialiseObservers() {
        binding?.run {
            viewModel.run {
                connectivityLiveData.observe(viewLifecycleOwner) { isAvailable ->
                    when (isAvailable) {
                        true -> {
                            retryButton.visibility = View.GONE
                            customConstraintLayout.visibility = View.VISIBLE
                            initialiseOnePokemonObserver()
                        }
                        false -> {
                            retryButton.visibility = View.VISIBLE
                            customConstraintLayout.visibility = View.GONE
                        }
                    }
                }
                initialiseOnePokemonObserver()
                pokemonLoadingStateLiveData.observe(viewLifecycleOwner) {
                    onPokemonLoadingStateChanged(it)
                }
            }
        }
    }

    private fun initialiseOnePokemonObserver() {
        binding?.run {
            viewModel.onePokemon.observe(viewLifecycleOwner) { pokemon ->
                imagePokemonImageView.loadImage(pokemon.sprites.front_default)
                nameTextView.text = pokemon.name
                pokemon.types.forEach {
                    typeTextView.append("${it.type.name} \n")
                }
                weightTextView.text = "${(pokemon.weight / 10)} ${getString(R.string.kg)}"
                heightTextView.text = "${(pokemon.height * 10)} ${getString(R.string.cm)}"
                stopShimmerAnimation()
            }
        }
        arguments?.getInt(ID_POKEMON)?.let { id ->
            viewModel.getOnePokemon(id)
        }
    }

    private fun onPokemonLoadingStateChanged(state: PokemonLoadingState) {
        binding?.run {
            when (state) {
                PokemonLoadingState.LOADING -> {
                    startShimmerAnimation()
                }
                PokemonLoadingState.LOADED -> {
                    stopShimmerAnimation()
                }
                PokemonLoadingState.ERROR -> {
                    retryButton.visibility = View.VISIBLE
                    stopShimmerAnimation()
                    customConstraintLayout.visibility = View.GONE
                }
            }
        }
    }

    private fun startShimmerAnimation() {
        binding?.run {
            shimmerImage.startShimmerAnimation()
            shimmerName.startShimmerAnimation()
            shimmerHeight.startShimmerAnimation()
            shimmerTypes.startShimmerAnimation()
            shimmerWeight.startShimmerAnimation()
        }
    }

    private fun stopShimmerAnimation() {
        binding?.run {
            shimmerImage.stopShimmerAnimation()
            shimmerName.stopShimmerAnimation()
            shimmerHeight.stopShimmerAnimation()
            shimmerTypes.stopShimmerAnimation()
            shimmerWeight.stopShimmerAnimation()
            imagePokemonImageView.background = null
            nameTextView.background = null
            heightTextView.background = null
            typeTextView.background = null
            weightTextView.background = null
        }
    }
}