package com.ilein.cosmodroid.core

import android.app.Application
import com.ilein.cosmodroid.feature_news_list.di.newsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DiApp)
            modules(coreModule, newsModule)
        }
    }
}