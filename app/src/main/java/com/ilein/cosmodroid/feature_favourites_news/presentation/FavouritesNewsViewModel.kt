package com.ilein.cosmodroid.feature_favourites_news.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilein.cosmodroid.feature_favourites_news.domain.DateState
import com.ilein.cosmodroid.feature_favourites_news.domain.interactor.FavoriteInteractor
import com.ilein.cosmodroid.feature_favourites_news.presentation.model.DbNewsItem
import com.ilein.cosmodroid.feature_favourites_news.presentation.model.dbNewsToDbNewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritesNewsViewModel(private val favoriteInteractor: FavoriteInteractor) : ViewModel() {

    private val newsViewState = MutableLiveData<FavoritesViewState>()
    val contentState: LiveData<FavoritesViewState> get() = newsViewState

    fun getFavoriteNewsList() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val news = favoriteInteractor.getNewsList()) {
                is DateState.Data -> dispatcherSuccess(news.data.map { it.dbNewsToDbNewsItem() })
                is DateState.Empty -> dispatcherError()
            }
        }
    }

    private suspend fun dispatcherSuccess(list: List<DbNewsItem>) {
        withContext(Dispatchers.Main) {
            newsViewState.value = FavoritesViewState.Content(list)
        }
    }

    private suspend fun dispatcherError() {
        withContext(Dispatchers.Main) {
            newsViewState.value = FavoritesViewState.EmptyDatabase
        }
    }

    fun removeItem(itemId: Int) {
        newsViewState.value?.ifContent {
            val newNewsList = it.newsList.toMutableList().filter { dbNewsItem -> dbNewsItem.id != itemId }
            newsViewState.value =
                if (newNewsList.isEmpty()) FavoritesViewState.EmptyDatabase else FavoritesViewState.Content(newNewsList)
        }
    }
}