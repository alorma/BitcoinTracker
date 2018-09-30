package com.alorma.btctracker.domain.repository

import com.alorma.btctracker.data.charts.GetChartData
import com.alorma.btctracker.domain.charts.ChartData
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test

class ChartRepositoryTest {

    private lateinit var repo: ChartRepository

    private val networkGet: GetChartData = mock()

    private lateinit var localDS: LocalChartDataSource

    @Before
    fun setUp() {
        localDS = LocalChartDataSource()
        repo = ChartRepository(networkGet, localDS, localDS)
    }

    @Test
    fun `on retrieve chart data if local is empty call network`() {
        given(networkGet.getChartData(any())).willReturn(Maybe.empty())

        repo.getChartData(mock()).test()

        verify(networkGet).getChartData(any())
    }

    @Test
    fun `on retrieve chart data if local is not empty not call network`() {
        val net = Maybe.empty<ChartData>()
        given(networkGet.getChartData(any())).willReturn(net)
        localDS.save(mock())

        repo.getChartData(mock()).test()

        verifyZeroInteractions(networkGet)
    }

    @Test
    fun `on retrieve chart data if local empty call save`() {
        val value = mock<ChartData>()
        val net = Maybe.just(value)

        given(networkGet.getChartData(any())).willReturn(net)

        repo.getChartData(mock()).test()

        localDS.getChartData(mock()).test().assertValue(value)
    }
}