package com.alorma.btctracker.data.charts.network

import assertk.all
import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.Before
import org.junit.Test
import java.util.*

class DateMapperTest {

    private lateinit var mapper: DateMapper

    @Before
    fun setUp() {
        mapper = DateMapper()
    }

    @Test
    fun `on value 0 map date with start time`() {
        val date = mapper.map(0)

        assert(date).all {
            assert(actual.time).isEqualTo(0L)
        }
    }

    @Test
    fun `on value 1506729600 map date with Saturday 30th September 2017 02_00_00 AM`() {
        val date = mapper.map(15067296000)

        assert(date).all {
            Calendar.getInstance().apply {
                timeInMillis = actual.time
                assert(get(Calendar.DAY_OF_MONTH)).isEqualTo(30)
                assert(get(Calendar.MONTH)).isEqualTo(Calendar.SEPTEMBER)
                assert(get(Calendar.YEAR)).isEqualTo(2017)
            }
        }
    }
}