package com.example.spacenews

import android.app.Application
import com.example.spacenews.data.di.DataModule
import com.example.spacenews.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        PresentationModule.load()
        DataModule.load()
        DomainModule.load()
    }
}