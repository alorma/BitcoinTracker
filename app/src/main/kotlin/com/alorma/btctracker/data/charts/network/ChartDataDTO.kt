package com.alorma.btctracker.data.charts.network

import com.google.gson.annotations.SerializedName

data class ChartDataDTO(
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("period")
        val period: String,
        @SerializedName("unir")
        val unit: String,
        @SerializedName("values")
        val values: List<ChartPointDTO>
)

data class ChartPointDTO(val x: Long, val y: Double)