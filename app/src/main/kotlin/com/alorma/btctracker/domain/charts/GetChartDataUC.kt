package com.alorma.btctracker.domain.charts

import com.alorma.btctracker.domain.repository.ChartRepository
import io.reactivex.Single
import javax.inject.Inject

class GetChartDataUC @Inject constructor(
        private val chartRepository: ChartRepository
) {

    fun execute(timeStamp: ChartTimeStamp): Single<ChartData> = chartRepository.getChartData(timeStamp)
}