package com.alorma.btctracker.data.charts.network

import java.util.*

class DateMapper {

    fun map(it: Long): Date = Date(it * TIME_API_MULTIPLIER)

    companion object {
        private const val TIME_API_MULTIPLIER = 100
    }

}