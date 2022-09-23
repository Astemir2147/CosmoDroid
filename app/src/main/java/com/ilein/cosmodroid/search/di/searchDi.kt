package com.ilein.cosmodroid.search.di

import com.ilein.cosmodroid.search.data.api.RetrofitInstance
import com.ilein.cosmodroid.search.data.repository.SearchRepositoryImpl
import com.ilein.cosmodroid.search.domain.Interactor
import com.ilein.cosmodroid.search.domain.InteractorImpl
import com.ilein.cosmodroid.search.domain.SearchRepository
import com.ilein.cosmodroid.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    single { RetrofitInstance }
    factory<SearchRepository> { SearchRepositoryImpl() }
    factory<Interactor> { InteractorImpl(get()) }
    viewModel { SearchViewModel(get()) }
}