package com.alorma.btctracker.ui.chart

import android.support.test.espresso.matcher.ViewMatchers.withId
import com.alorma.btctracker.R
import com.alorma.btctracker.config.ProjectTestRule
import com.alorma.btctracker.config.configureRxThreading
import com.alorma.btctracker.domain.charts.ChartData
import com.alorma.btctracker.domain.charts.ChartPoint
import com.alorma.btctracker.domain.charts.Currency
import com.alorma.btctracker.domain.repository.ChartRepository
import com.alorma.btctracker.matcher.matchEntriesCount
import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.schibsted.spain.barista.internal.assertAny
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
    fun on_load_chart_data_render_chart_with_same_entries_count() {
        val items = 3
        given(chartRepository.getChartData(anyOrNull())).willReturn(Single.just(createChartData(items)))

        rule.run()

        withId(R.id.lineChart).assertAny(matchEntriesCount(items))
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