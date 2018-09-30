package com.alorma.btctracker.data.charts

import com.alorma.btctracker.domain.charts.ChartData
import com.alorma.btctracker.domain.charts.ChartTimeStamp
import io.reactivex.Maybe

interface GetChartData {
    fun getChartData(timeStamp: ChartTimeStamp?): Maybe<ChartData>
}

interface SaveChartData {
    fun save(chartData: ChartData)
}