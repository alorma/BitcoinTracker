package com.alorma.btctracker

import android.app.Application
import com.alorma.btctracker.di.AppComponent
import com.alorma.btctracker.di.AppModule
import com.alorma.btctracker.di.DaggerAppComponent

class BTCTrackerApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        val component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        setupComponent(component)
    }

    fun setupComponent(component: AppComponent) {
        this.component = component
    }
}