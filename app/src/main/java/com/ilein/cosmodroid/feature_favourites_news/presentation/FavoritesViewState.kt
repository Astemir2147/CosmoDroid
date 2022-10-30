package com.ilein.cosmodroid.feature_favourites_news.presentation

import com.ilein.cosmodroid.feature_favourites_news.presentation.model.DbNewsItem

sealed class FavoritesViewState {
    object EmptyDatabase : FavoritesViewState()

    data class Content(var newsList: List<DbNewsItem>) : FavoritesViewState()
}