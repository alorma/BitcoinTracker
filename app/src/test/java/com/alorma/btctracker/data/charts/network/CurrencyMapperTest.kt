package com.alorma.btctracker.data.charts.network

import assertk.all
import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.Before
import org.junit.Test

class CurrencyMapperTest {

    private lateinit var mapper: CurrencyMapper

    @Before
    fun setUp() {
        mapper = CurrencyMapper()
    }

    @Test
    fun `on map USD return usa dollars`() {
        val currency = mapper.map("USD")

        assert(currency).all {
            assert(actual.name).isEqualTo("USD")
            assert(actual.symbol).isEqualTo("$")
        }
    }

    @Test
    fun `on map EUR return euro`() {
        val currency = mapper.map("EUR")

        assert(currency).all {
            assert(actual.name).isEqualTo("EUR")
            assert(actual.symbol).isEqualTo("€")
        }
    }

    @Test
    fun `on map any other return same as value`() {
        val currency = mapper.map("XYZ")

        assert(currency).all {
            assert(actual.name).isEqualTo("XYZ")
            assert(actual.symbol).isEqualTo("XYZ")
        }
    }
}