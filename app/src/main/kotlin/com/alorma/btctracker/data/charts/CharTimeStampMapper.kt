package com.alorma.btctracker.data.charts

import com.alorma.btctracker.domain.charts.ChartTimeStamp

class CharTimeStampMapper {

    fun map(timeStamp: ChartTimeStamp): String {
        return when {
            timeStamp.value < 1 -> throw IllegalArgumentException()
            timeStamp.value == 1 -> "${timeStamp.value}${mapSingular[timeStamp.unit]}"
            else -> "${timeStamp.value}${mapPlural[timeStamp.unit]}"
        }
    }

    companion object {
        private val mapSingular: Map<ChartTimeStamp.Time, String> = mapOf(
                ChartTimeStamp.Time.DAY to "day",
                ChartTimeStamp.Time.MONTH to "month",
                ChartTimeStamp.Time.YEAR to "year"
        )
        private val mapPlural: Map<ChartTimeStamp.Time, String> = mapOf(
                ChartTimeStamp.Time.DAY to "days",
                ChartTimeStamp.Time.MONTH to "months",
                ChartTimeStamp.Time.YEAR to "years"
        )
    }
}