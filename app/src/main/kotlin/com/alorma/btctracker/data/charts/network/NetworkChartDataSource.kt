package com.alorma.btctracker.data.charts.network

import com.alorma.btctracker.data.charts.CharTimeStampMapper
import com.alorma.btctracker.data.charts.GetChartData
import com.alorma.btctracker.domain.charts.ChartData
import com.alorma.btctracker.domain.charts.ChartTimeStamp
import io.reactivex.Maybe

class NetworkChartDataSource(
        private val api: ChartAPI,
        private val timeStampMapper: CharTimeStampMapper,
        private val dataMapper: NetworkChartMapper
) : GetChartData {

    override fun getChartData(timeStamp: ChartTimeStamp): Maybe<ChartData> =
            Maybe.defer {
                val time = timeStampMapper.map(timeStamp)
                api.getChart(time).toMaybe()
            }.map { dataMapper.map(it) }
}