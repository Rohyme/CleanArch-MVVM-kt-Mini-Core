package com.rohyme.kotlin_core.presentation.myApplication

import android.app.Application
import com.rohyme.kotlin_core.presentation.di.network.networkModule
import com.rohyme.kotlin_core.presentation.ui.screens.testScreen.testModule
import com.rohyme.kotlin_core.presentation.utils.StateView
import com.tripl3dev.prettystates.StatesConfigFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 *
 * @Auther Rohyme
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.DEBUG)
            modules(networkModule, testModule)
        }

        StatesConfigFactory.intialize().initDefaultViews()
    }
}