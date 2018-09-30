package com.alorma.btctracker.config

import android.support.test.InstrumentationRegistry
import com.alorma.btctracker.BTCTrackerApplication
import com.alorma.btctracker.di.AppComponent
import com.alorma.btctracker.di.AppModule
import it.cosenonjaviste.daggermock.DaggerMock
import it.cosenonjaviste.daggermock.DaggerMockRule

fun getDaggerRule(): DaggerMockRule<AppComponent> =
        DaggerMock.rule(AppModule(app)) {
            set { component ->
                app.setupComponent(component)
            }
        }

val app: BTCTrackerApplication get() = InstrumentationRegistry.getTargetContext().applicationContext as BTCTrackerApplication