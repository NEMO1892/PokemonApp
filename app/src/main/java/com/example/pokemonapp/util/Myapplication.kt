package com.example.pokemonapp.util

import android.app.Application
import com.example.pokemonapp.di.ApplicationComponent
import com.example.pokemonapp.di.DaggerApplicationComponent
import com.example.pokemonapp.di.DatabaseModule

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent =
            DaggerApplicationComponent.builder().databaseModule(DatabaseModule(this, this)).build()
    }

    companion object {
        lateinit var appComponent: ApplicationComponent
    }
}