package com.example.pokemonapp.di

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent =
            DaggerApplicationComponent.builder().databaseModule(DatabaseModule(this)).build()
    }

    companion object {
        lateinit var appComponent: ApplicationComponent
    }
}