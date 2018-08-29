package com.celestialapps.unsplash

import android.app.Application

import com.celestialapps.unsplash.dager.components.AppComponent
import com.celestialapps.unsplash.dager.components.DaggerAppComponent
import com.celestialapps.unsplash.dager.modules.ApiModule
import com.celestialapps.unsplash.dager.modules.ContextModule


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = buildAppComponent()
    }

    protected fun buildAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .contextModule(ContextModule(this))
                .build()
    }

    companion object {


        var appComponent: AppComponent? = null
            private set
    }

}
