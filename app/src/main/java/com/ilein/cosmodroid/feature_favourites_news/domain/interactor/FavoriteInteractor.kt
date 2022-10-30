package com.ilein.cosmodroid.feature_favourites_news.domain.interactor

import com.ilein.cosmodroid.feature_favourites_news.domain.DateState
import com.ilein.cosmodroid.feature_favourites_news.domain.model.DbNewsPreviewModel

interface FavoriteInteractor {
    suspend fun getNewsList(): DateState<List<DbNewsPreviewModel>>
}