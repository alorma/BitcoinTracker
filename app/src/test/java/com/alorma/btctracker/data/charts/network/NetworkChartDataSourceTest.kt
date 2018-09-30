package com.alorma.btctracker.data.charts.network

import com.alorma.btctracker.data.charts.CharTimeStampMapper
import com.alorma.btctracker.domain.charts.ChartTimeStamp
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class NetworkChartDataSourceTest {

    private lateinit var ds: NetworkChartDataSource

    private val api: ChartAPI = mock()

    @Before
    fun setUp() {
        val dataMapper = NetworkChartMapper(CurrencyMapper(), DateMapper())
        ds = NetworkChartDataSource(api, CharTimeStampMapper(), dataMapper)
    }

    @Test
    fun `on invalid timestmap returns Maybe error()`() {
        val time = ChartTimeStamp(0, ChartTimeStamp.Time.DAY)

        ds.getChartData(time).test().assertError(IllegalArgumentException::class.java)
    }

    @Test
    fun `on valid timestmap calls api with mapped value`() {
        val time = ChartTimeStamp(3, ChartTimeStamp.Time.DAY)

        ds.getChartData(time).test()

        verify(api).getChart(eq("3days"))
    }

    @Test
    fun `on api returns valid data returns mapped`() {
        val elements = 3
        given(api.getChart(anyString())).willReturn(Single.just(createData(elements)))

        val time = ChartTimeStamp(3, ChartTimeStamp.Time.DAY)

        val test = ds.getChartData(time).test()

        test.assertValue {
            it.values.size == elements
        }
    }

    private fun createData(items: Int): ChartDataDTO = ChartDataDTO(
            "Data",
            "Data description",
            "day",
            "USD",
            createItems(items)
    )

    private fun createItems(items: Int): List<ChartPointDTO> = (0 until items).map {
        ChartPointDTO(it.toLong(), it.toDouble())
    }
}