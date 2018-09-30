package com.alorma.btctracker.data.charts.network

import java.util.*
import javax.inject.Inject

class DateMapper @Inject constructor() {

    fun map(it: Long): Date = Date(it * TIME_API_MULTIPLIER)

    companion object {
        private const val TIME_API_MULTIPLIER = 100
    }

}