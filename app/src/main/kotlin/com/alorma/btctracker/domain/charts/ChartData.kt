package com.alorma.btctracker.domain.charts

import java.util.*

data class ChartData(val name: String, val description: String, val values: List<ChartPoint>)

data class ChartPoint(val x: Float, val y: Float, val date: Date, val price: Double, val currency: Currency)

data class Currency(val symbol: String, val name: String)