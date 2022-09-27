package com.ilein.cosmodroid.feature_news_list.di

import com.ilein.cosmodroid.feature_news_list.data.api.ApiRequest
import com.ilein.cosmodroid.feature_news_list.presentation.NewsViewModel
import com.ilein.cosmodroid.feature_news_list.data.repository.NewsRepositoryImpl
import com.ilein.cosmodroid.feature_news_list.domain.interactor.NewsInteractorImpl
import com.ilein.cosmodroid.feature_news_list.domain.interactor.NewsInteractor
import com.ilein.cosmodroid.feature_news_list.domain.repository.NewsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val newsModule = module {
    factory<NewsRepository> { NewsRepositoryImpl(get()) }
    factory<NewsInteractor> { NewsInteractorImpl(get()) }
    viewModel { NewsViewModel(get()) }
    single<ApiRequest> {
        val retrofit =
            Retrofit.Builder()
                .baseUrl("https://ll.thespacedevs.com/2.2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(ApiRequest::class.java)
    }

}