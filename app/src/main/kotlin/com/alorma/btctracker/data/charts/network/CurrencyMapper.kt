package com.alorma.btctracker.data.charts.network

import com.alorma.btctracker.domain.charts.Currency
import javax.inject.Inject

class CurrencyMapper @Inject constructor() {

    fun map(unit: String): Currency = currencies[unit] ?: Currency(unit, unit)

    companion object {
        private val currencies: Map<String, Currency> = mapOf(
                "USD" to Currency("$", "USD"),
                "EUR" to Currency("€", "EUR")
        )
    }
}