package com.alorma.btctracker.data.charts.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ChartAPI {
    @GET("charts/market-price")
    fun getChart(@Query("timespan") timespan: String): Single<ChartDataDTO>
}