package com.alorma.btctracker.di

import android.content.Context
import com.alorma.btctracker.ui.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface AppComponent {

    fun getContext(): Context

    fun inject(mainActivity: MainActivity)
}