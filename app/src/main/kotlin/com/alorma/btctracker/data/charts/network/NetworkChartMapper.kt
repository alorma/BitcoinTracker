package com.alorma.btctracker.data.charts.network

import com.alorma.btctracker.domain.charts.ChartData
import com.alorma.btctracker.domain.charts.ChartPoint
import com.alorma.btctracker.domain.charts.Currency
import javax.inject.Inject

class NetworkChartMapper @Inject constructor(
        private val currencyMapper: CurrencyMapper,
        private val dateMapper: DateMapper
) {
    fun map(it: ChartDataDTO): ChartData = ChartData(
            it.name,
            it.description,
            currencyMapper.map(it.unit).let { currency -> it.values.map { mapPoint(it, currency) } }
    )

    private fun mapPoint(it: ChartPointDTO, currency: Currency): ChartPoint = ChartPoint(
            it.x.toDouble(),
            it.y,
            dateMapper.map(it.x),
            it.y,
            currency
    )
}