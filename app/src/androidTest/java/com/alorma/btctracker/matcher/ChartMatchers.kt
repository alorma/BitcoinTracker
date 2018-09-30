package com.alorma.btctracker.matcher

import android.view.View
import com.github.mikephil.charting.charts.Chart
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun matchDataSets(dataSetsCount: Int): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description) {
        description.appendText("with dat sets count: ").appendText(dataSetsCount.toString())
    }

    override fun matchesSafely(item: View): Boolean = (item as? Chart<*>)?.data?.dataSetCount == dataSetsCount
}

fun matchEntriesCount(entriesCount: Int): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description) {
        description.appendText("with entries count: ").appendText(entriesCount.toString())
    }

    override fun matchesSafely(item: View): Boolean = (item as? Chart<*>)?.data?.entryCount == entriesCount
}