package com.example.pokemonapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.presentation.list.ListPokemonsFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ListPokemonsFragment())
            .commit()
    }
}