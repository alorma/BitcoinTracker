package com.alorma.btctracker.config

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.alorma.btctracker.BTCTrackerApplication
import com.github.tmurakami.dexopener.DexOpener

class DexOpenerAndroidJUnitRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        DexOpener.builder(context)
                .buildConfig(com.alorma.btctracker.BuildConfig::class.java) // Set the BuildConfig class
                .build()
                .installTo(cl)
        return super.newApplication(cl, BTCTrackerApplication::class.java.name, context)
    }
}