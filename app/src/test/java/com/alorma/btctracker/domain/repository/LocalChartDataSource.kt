package com.alorma.btctracker.domain.repository

import com.alorma.btctracker.data.charts.GetChartData
import com.alorma.btctracker.data.charts.SaveChartData
import com.alorma.btctracker.domain.charts.ChartData
import com.alorma.btctracker.domain.charts.ChartTimeStamp
import io.reactivex.Maybe

class LocalChartDataSource : GetChartData, SaveChartData {

    private var chart: ChartData? = null

    override fun getChartData(timeStamp: ChartTimeStamp?): Maybe<ChartData> =
            chart?.let { Maybe.just(it) } ?: Maybe.empty()

    override fun save(chartData: ChartData) {
        this.chart = chartData
    }
}