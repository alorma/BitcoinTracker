package com.alorma.btctracker.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alorma.btctracker.R
import com.alorma.btctracker.domain.charts.ChartData
import com.alorma.btctracker.domain.charts.GetChartDataUC
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getChartData: GetChartDataUC

    private val disposable: CompositeDisposable = CompositeDisposable()

    private lateinit var dataSet: LineDataSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        appComponent { inject(this@MainActivity) }

        val dispose = getChartData.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onDataReceived(it)
                }, {
                    onError(it)
                })

        disposable.add(dispose)
    }

    private fun onDataReceived(it: ChartData) {
        with(lineChart) {
            val values = it.values.map { Entry(it.x.toFloat(), it.y.toFloat()) }
            if (data == null || data.dataSetCount == 0) {
                description.isEnabled = false
                legend.isEnabled = false
                xAxis.isEnabled = false
                axisRight.isEnabled = false
                setNoDataText("")

                setTouchEnabled(false)
                setPinchZoom(false)

                dataSet = LineDataSet(values, "DataSet 1").apply {
                    configLine()
                }

                data = LineData(listOf(dataSet))
            } else {
                dataSet.values = values
                dataSet.notifyDataSetChanged()
                notifyDataSetChanged()
            }
            animateX(1000)
            animateY(1000)
        }
    }

    private fun LineDataSet.configLine() {
        setDrawIcons(false)
        color = Color.BLACK
        lineWidth = 1f
        valueTextSize = 14f
        setDrawCircles(false)
    }

    private fun onError(it: Throwable) {
        it.printStackTrace()
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
    }
}