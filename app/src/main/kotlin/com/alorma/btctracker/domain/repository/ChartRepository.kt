package com.alorma.btctracker.domain.repository

import com.alorma.btctracker.data.charts.GetChartData
import com.alorma.btctracker.data.charts.SaveChartData
import com.alorma.btctracker.domain.charts.ChartData
import com.alorma.btctracker.domain.charts.ChartTimeStamp
import io.reactivex.Maybe
import io.reactivex.Single

class ChartRepository(
        private val networkGetChartData: GetChartData,
        private val localGetChartData: GetChartData,
        private val saveChartData: SaveChartData
) {

    fun getChartData(timeStamp: ChartTimeStamp?): Single<ChartData> =
            localGetChartData.getChartData(timeStamp)
                    .switchIfEmpty(Maybe.defer {
                        networkGetChartData.getChartData(timeStamp).doOnSuccess {
                            saveChartData.save(it)
                        }
                    }
                    )
                    .toSingle()
}