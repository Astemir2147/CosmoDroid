package com.ilein.cosmodroid.feature_news_list.presentation

import androidx.lifecycle.LiveData
import com.ilein.cosmodroid.feature_news_list.domain.interactor.NewsInteractor
import com.ilein.cosmodroid.feature_news_list.domain.ResultState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.model.parseError
import com.ilein.cosmodroid.feature_news_list.presentation.model.toNewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.state.NewsViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val newsInteractor: NewsInteractor): ViewModel() {

    private val newsViewState = MutableLiveData<NewsViewState>()
    val contentState: LiveData<NewsViewState> get() = newsViewState

    fun getNewsList() {
        viewModelScope.launch {
            newsViewState.value = NewsViewState.Shimmer
            when (val news = newsInteractor.getNewsList()) {
                is ResultState.Success -> { dispatcherSuccess(news.data.map { it.toNewsItem() }) }
                is ResultState.Error -> { dispatcherError(news.errorData) }
            }
        }
    }

    private suspend fun dispatcherSuccess(list: List<NewsItem>) {
        withContext(Dispatchers.Main) {
            newsViewState.postValue(NewsViewState.Content(newsList = list))
        }

    }

    private suspend fun dispatcherError(isNetworkError: Boolean) {
        withContext(Dispatchers.Main) {
            newsViewState.value = NewsViewState.Error(error = isNetworkError.parseError())
        }
    }

}