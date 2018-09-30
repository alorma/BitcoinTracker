package com.alorma.btctracker.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideAppContext(): Context = context

}