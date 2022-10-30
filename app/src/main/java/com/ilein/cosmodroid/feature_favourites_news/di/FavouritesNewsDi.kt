package com.ilein.cosmodroid.feature_favourites_news.di

import com.ilein.cosmodroid.core.database.dao.NewsDao
import com.ilein.cosmodroid.feature_favourites_news.data.repository.FavouritesNewsRepositoryImpl
import com.ilein.cosmodroid.feature_favourites_news.domain.interactor.FavoriteInteractor
import com.ilein.cosmodroid.feature_favourites_news.domain.interactor.FavoriteInteractorImpl
import com.ilein.cosmodroid.feature_favourites_news.domain.repository.FavouritesNewsRepository
import com.ilein.cosmodroid.feature_favourites_news.presentation.FavouritesNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouritesNewsModule = module {
    factory<FavouritesNewsRepository> { FavouritesNewsRepositoryImpl(dao = get() as NewsDao) }
    factory<FavoriteInteractor> { FavoriteInteractorImpl(repository = get()) }
    viewModel { FavouritesNewsViewModel(favoriteInteractor = get()) }
}
