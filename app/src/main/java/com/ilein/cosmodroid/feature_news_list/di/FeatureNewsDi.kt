package com.ilein.cosmodroid.feature_news_list.di

import com.ilein.cosmodroid.feature_news_list.presentation.NewsViewModel
import com.ilein.cosmodroid.feature_news_list.data.repository.NewsRepositoryImpl
import com.ilein.cosmodroid.feature_news_list.domain.interactor.NewsInteractorImpl
import com.ilein.cosmodroid.feature_news_list.domain.interactor.NewsInteractor
import com.ilein.cosmodroid.feature_news_list.domain.repository.NewsRepository
import com.ilein.cosmodroid.feature_news_list.presentation.news_detail.DetailNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsModule = module {
    factory<NewsRepository> { NewsRepositoryImpl(get()) }
    factory<NewsInteractor> { NewsInteractorImpl(get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { DetailNewsViewModel(get()) }
}