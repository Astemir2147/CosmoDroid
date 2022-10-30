package com.ilein.cosmodroid.core.di

import androidx.room.Room
import com.ilein.cosmodroid.core.api.AddLoggingInterceptor
import com.ilein.cosmodroid.core.api.ApiRequest
import com.ilein.cosmodroid.core.database.dao.NewsDao
import com.ilein.cosmodroid.feature_favourites_news.data.MyDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreModule = module{
    single<ApiRequest> {
        val retrofit =
            Retrofit.Builder()
                .baseUrl("https://ll.thespacedevs.com/2.2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(AddLoggingInterceptor.setLogging())
                .build()
        retrofit.create(ApiRequest::class.java)
    }

    single<MyDatabase>{
        Room.databaseBuilder(androidApplication(), MyDatabase::class.java, "MyDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<NewsDao> { get<MyDatabase>().getDao() }
}