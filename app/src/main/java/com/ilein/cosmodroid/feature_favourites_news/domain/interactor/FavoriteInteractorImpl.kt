package com.ilein.cosmodroid.feature_favourites_news.domain.interactor

import com.ilein.cosmodroid.feature_favourites_news.domain.DateState
import com.ilein.cosmodroid.feature_favourites_news.domain.model.DbNewsPreviewModel
import com.ilein.cosmodroid.feature_favourites_news.domain.repository.FavouritesNewsRepository

class FavoriteInteractorImpl(private val repository: FavouritesNewsRepository) :
    FavoriteInteractor {
    override suspend fun getNewsList(): DateState<List<DbNewsPreviewModel>> =
        safeDbRequest(repository.getNewsList())
}