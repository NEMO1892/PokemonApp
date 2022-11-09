package com.example.pokemonapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonapp.R
import com.example.pokemonapp.presentation.list.ListPokemonsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ListPokemonsFragment())
            .commit()
    }
}