package com.ilein.cosmodroid.feature_favourites_news.data.repository

import com.ilein.cosmodroid.core.database.dao.NewsDao
import com.ilein.cosmodroid.feature_favourites_news.data.model.toDbPreviewModel
import com.ilein.cosmodroid.feature_favourites_news.domain.model.DbNewsPreviewModel
import com.ilein.cosmodroid.feature_favourites_news.domain.repository.FavouritesNewsRepository

class FavouritesNewsRepositoryImpl(private val dao: NewsDao): FavouritesNewsRepository {
    override suspend fun getNewsList(): List<DbNewsPreviewModel> {
        return dao.getAllFavouritesNews().map { it.toDbPreviewModel() }
    }
}