package com.alorma.btctracker.data.charts

import assertk.assert
import assertk.assertions.isEqualTo
import com.alorma.btctracker.domain.charts.ChartTimeStamp
import org.junit.Before
import org.junit.Test

class CharTimeStampMapperTest {

    private lateinit var mapper: CharTimeStampMapper

    @Before
    fun setUp() {
        mapper = CharTimeStampMapper()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `on zero value throw IllegalArgumentException`() {
        val timeStamp = ChartTimeStamp(0, ChartTimeStamp.Time.DAY)
        mapper.map(timeStamp)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `on negative value throw IllegalArgumentException`() {
        val timeStamp = ChartTimeStamp(-1, ChartTimeStamp.Time.DAY)
        mapper.map(timeStamp)
    }

    @Test
    fun `on value 1 day, return 1day string`() {
        val timeStamp = ChartTimeStamp(1, ChartTimeStamp.Time.DAY)
        val value = mapper.map(timeStamp)

        assert(value).isEqualTo("1day")
    }

    @Test
    fun `on value 2 day, return 2days string`() {
        val timeStamp = ChartTimeStamp(2, ChartTimeStamp.Time.DAY)
        val value = mapper.map(timeStamp)

        assert(value).isEqualTo("2days")
    }

    @Test
    fun `on value 1 month, return 1month string`() {
        val timeStamp = ChartTimeStamp(1, ChartTimeStamp.Time.MONTH)
        val value = mapper.map(timeStamp)

        assert(value).isEqualTo("1month")
    }

    @Test
    fun `on value 2 months, return 2months string`() {
        val timeStamp = ChartTimeStamp(2, ChartTimeStamp.Time.MONTH)
        val value = mapper.map(timeStamp)

        assert(value).isEqualTo("2months")
    }

    @Test
    fun `on value 1 year, return 1year string`() {
        val timeStamp = ChartTimeStamp(1, ChartTimeStamp.Time.YEAR)
        val value = mapper.map(timeStamp)

        assert(value).isEqualTo("1year")
    }

    @Test
    fun `on value 2 year, return 2years string`() {
        val timeStamp = ChartTimeStamp(2, ChartTimeStamp.Time.YEAR)
        val value = mapper.map(timeStamp)

        assert(value).isEqualTo("2years")
    }
}