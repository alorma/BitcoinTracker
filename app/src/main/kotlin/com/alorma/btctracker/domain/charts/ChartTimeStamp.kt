package com.alorma.btctracker.domain.charts

data class ChartTimeStamp(val value: Int, val unit: Time) {
    enum class Time {
        DAY,
        MONTH,
        YEAR
    }
}