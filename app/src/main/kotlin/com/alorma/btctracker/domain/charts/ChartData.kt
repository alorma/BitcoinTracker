package com.alorma.btctracker.domain.charts

import java.util.*

data class ChartData(val name: String, val description: String, val values: List<ChartPoint>)

data class ChartPoint(val x: Double, val y: Double, val date: Date, val price: Double, val currency: Currency)

data class Currency(val symbol: String, val name: String)