package com.alorma.btctracker.domain.charts

import io.reactivex.Single

class GetChartDataUC {

    fun execute(timeStamp: ChartTimeStamp): Single<ChartData> = Single.never()
}