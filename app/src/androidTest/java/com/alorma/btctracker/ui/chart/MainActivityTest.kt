package com.alorma.btctracker.ui.chart

import com.alorma.btctracker.R
import com.alorma.btctracker.config.ProjectTestRule
import com.alorma.btctracker.config.configureRxThreading
import com.alorma.btctracker.domain.charts.ChartData
import com.alorma.btctracker.domain.charts.ChartPoint
import com.alorma.btctracker.domain.charts.Currency
import com.alorma.btctracker.domain.repository.ChartRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import java.util.*

class MainActivityTest {

    @get:Rule
    val rule = ProjectTestRule(MainActivity::class.java, this)

    val chartRepository: ChartRepository = mock()

    init {
        configureRxThreading()
    }

    @Test
    fun on_load_chart_data_render() {
        given(chartRepository.getChartData(any())).willReturn(Single.just(createChartData(3)))

        rule.run()

        assertDisplayed(R.id.lineChart)
    }

    private fun createChartData(items: Int): ChartData = ChartData(
            "Chart data",
            "Chart data description",
            createChartItems(items)
    )

    private fun createChartItems(times: Int): List<ChartPoint> = (0 until times).map {
        ChartPoint(
                it.toDouble(),
                it.toDouble(),
                Date(),
                3.5,
                Currency("$", "EUR")
        )
    }
}