package com.ilein.cosmodroid.feature_news_list.di

import com.ilein.cosmodroid.feature_news_list.presentation.NewsViewModel
import com.ilein.cosmodroid.feature_news_list.data.api.RetrofitInstance
import com.ilein.cosmodroid.feature_news_list.data.repository.RepositoryImpl
import com.ilein.cosmodroid.feature_news_list.domain.interactor.InteractorImpl
import com.ilein.cosmodroid.feature_news_list.domain.interactor.Interactor
import com.ilein.cosmodroid.feature_news_list.domain.repository.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsModule = module {
    single { RetrofitInstance }
    factory<Repository> { RepositoryImpl() }
    factory<Interactor> { InteractorImpl(get()) }
    viewModel { NewsViewModel(get()) }
}