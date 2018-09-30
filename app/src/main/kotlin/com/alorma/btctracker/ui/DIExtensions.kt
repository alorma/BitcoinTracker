package com.alorma.btctracker.ui

import android.content.Context
import com.alorma.btctracker.BTCTrackerApplication
import com.alorma.btctracker.di.AppComponent

fun Context.appComponent(block: AppComponent.() -> Unit) {
    (applicationContext as? BTCTrackerApplication)?.component?.let(block)
}