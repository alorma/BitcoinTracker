package com.alorma.btctracker.di

import android.content.Context
import com.alorma.btctracker.domain.repository.ChartRepository
import com.alorma.btctracker.ui.chart.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface AppComponent {

    fun getContext(): Context

    fun providesChartRepository(): ChartRepository

    fun inject(mainActivity: MainActivity)
}