package com.alorma.btctracker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alorma.btctracker.R
import com.alorma.btctracker.domain.charts.ChartData
import com.alorma.btctracker.domain.charts.ChartTimeStamp
import com.alorma.btctracker.domain.charts.GetChartDataUC
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getChartData: GetChartDataUC

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        appComponent { inject(this@MainActivity) }

        val time = ChartTimeStamp(3, ChartTimeStamp.Time.DAY)

        val dispose = getChartData.execute(time)
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
        Toast.makeText(this, "$it.name - ${it.values.size}", Toast.LENGTH_SHORT).show()
    }

    private fun onError(it: Throwable) {
        Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        disposable.clear()
        super.onStop()
    }
}