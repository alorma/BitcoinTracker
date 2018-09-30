package com.alorma.btctracker.di

import com.alorma.btctracker.data.charts.CharTimeStampMapper
import com.alorma.btctracker.data.charts.GetChartData
import com.alorma.btctracker.data.charts.local.LocalChartDataSource
import com.alorma.btctracker.data.charts.network.ChartAPI
import com.alorma.btctracker.data.charts.network.NetworkChartDataSource
import com.alorma.btctracker.data.charts.network.NetworkChartMapper
import com.alorma.btctracker.domain.repository.ChartRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideNetworkGetChartData(
            chartAPI: ChartAPI,
            timeStampMapper: CharTimeStampMapper,
            dataMapper: NetworkChartMapper
    ): GetChartData = NetworkChartDataSource(chartAPI, timeStampMapper, dataMapper)

    @Provides
    fun localCache(): LocalChartDataSource = LocalChartDataSource

    @Provides
    fun provideChartRepository(
            networkGet: GetChartData,
            local: LocalChartDataSource
    ): ChartRepository = ChartRepository(networkGet, local, local)
}