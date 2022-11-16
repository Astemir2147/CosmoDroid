package com.ilein.cosmodroid.search.di

import com.ilein.cosmodroid.search.data.repository.SearchRepositoryImpl
import com.ilein.cosmodroid.search.domain.Interactor
import com.ilein.cosmodroid.search.domain.InteractorImpl
import com.ilein.cosmodroid.search.domain.SearchRepository
import com.ilein.cosmodroid.search.presentation.SearchItemDetailViewModel
import com.ilein.cosmodroid.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    factory<SearchRepository> { SearchRepositoryImpl(get()) }
    factory<Interactor> { InteractorImpl(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { SearchItemDetailViewModel(get()) }
}