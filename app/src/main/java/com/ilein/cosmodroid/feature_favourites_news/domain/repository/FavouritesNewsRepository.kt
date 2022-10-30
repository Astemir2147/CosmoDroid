package com.ilein.cosmodroid.feature_favourites_news.domain.repository

import com.ilein.cosmodroid.feature_favourites_news.domain.model.DbNewsPreviewModel

interface FavouritesNewsRepository {
    suspend fun getNewsList(): List<DbNewsPreviewModel>
}