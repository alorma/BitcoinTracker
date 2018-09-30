package com.alorma.btctracker.domain.charts

import com.alorma.btctracker.domain.repository.ChartRepository
import io.reactivex.Single

class GetChartDataUC(private val chartRepository: ChartRepository) {
    fun execute(timeStamp: ChartTimeStamp): Single<ChartData> = chartRepository.getChartData(timeStamp)
}