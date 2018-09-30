package com.alorma.btctracker.di

import android.content.Context
import com.alorma.btctracker.data.charts.GetChartData
import com.alorma.btctracker.data.charts.local.LocalChartDataSource
import com.alorma.btctracker.domain.repository.ChartRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideAppContext(): Context = context

    @Provides
    fun provideChartRepository(
            networkGet: GetChartData,
            local: LocalChartDataSource
    ): ChartRepository = ChartRepository(networkGet, local, local)

}