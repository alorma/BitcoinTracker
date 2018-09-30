package com.alorma.btctracker.di

import com.alorma.btctracker.data.charts.network.ChartAPI
import com.alorma.btctracker.data.charts.network.ChartDataDTO
import com.alorma.btctracker.data.charts.network.ChartPointDTO
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module
class NetworkModule {

    @Provides
    fun provideChartApi(): ChartAPI = object : ChartAPI {
        override fun getChart(timespan: String): Single<ChartDataDTO> = Single.fromCallable {
            ChartDataDTO(
                    "Market Price (USD)",
                    "Average USD market price across major bitcoin exchanges.",
                    "day",
                    "USD",
                    listOf(
                            ChartPointDTO(1506729600, 4335.368316666667),
                            ChartPointDTO(1506816000, 4360.722966666667)
                    )
            )
        }
    }
}