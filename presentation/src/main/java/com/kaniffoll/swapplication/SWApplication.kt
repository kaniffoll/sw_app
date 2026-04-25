package com.kaniffoll.swapplication

import android.app.Application
import com.kaniffoll.di.AppComponent
import com.kaniffoll.di.AppComponentProvider
import com.kaniffoll.di.DaggerAppComponent

class SWApplication: Application(), AppComponentProvider {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }

    override fun onCreate() {
        applicationContext
        super.onCreate()
    }
}