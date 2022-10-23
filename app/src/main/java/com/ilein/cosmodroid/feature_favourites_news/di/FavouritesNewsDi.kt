package com.ilein.cosmodroid.feature_favourites_news.di

import com.ilein.cosmodroid.core.database.dao.NewsDao
import com.ilein.cosmodroid.feature_favourites_news.data.MyDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val favouritesNewsModule = module {
    single { MyDatabase.buildDatabase(androidApplication()) }
    single<NewsDao> { get<MyDatabase>().getDao() }
}