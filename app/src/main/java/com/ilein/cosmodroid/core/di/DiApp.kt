package com.ilein.cosmodroid.core.di

import com.ilein.cosmodroid.feature_news_list.di.newsModule
import com.ilein.cosmodroid.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import android.app.Application
import com.ilein.cosmodroid.feature_favourites_news.di.favouritesNewsModule

class DiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DiApp)
            modules(coreModule, newsModule, searchModule, favouritesNewsModule)
        }
    }
}