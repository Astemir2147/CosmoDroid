package com.ilein.cosmodroid.feature_news_list.presentation.news_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilein.cosmodroid.feature_news_list.domain.ResultState
import com.ilein.cosmodroid.feature_news_list.domain.interactor.NewsInteractor
import com.ilein.cosmodroid.feature_news_list.presentation.state.DetailNewsViewState
import com.ilein.cosmodroid.feature_news_list.presentation.model.DetailNewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.model.parseError
import com.ilein.cosmodroid.feature_news_list.presentation.model.toDetailNewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailNewsViewModel(private val newsInteractor: NewsInteractor) : ViewModel() {
    private val newsViewState = MutableLiveData<DetailNewsViewState>()
    val contentState: LiveData<DetailNewsViewState> get() = newsViewState

    fun getNews(id: Int) {
        viewModelScope.launch {
            val news = newsInteractor.getDetailNews(id = id)
            newsViewState.value = DetailNewsViewState.Shimmer
            when (news) {
                is ResultState.Success -> { dispatcherSuccess(news.data.toDetailNewsItem()) }
                is ResultState.Error -> { dispatcherError(news.errorData) }
            }
        }
    }

    private suspend fun dispatcherSuccess(news: DetailNewsItem) {
        withContext(Dispatchers.Main) {
            newsViewState.postValue(DetailNewsViewState.Content(news = news))
        }

    }

    private suspend fun dispatcherError(isNetworkError: Boolean) {
        withContext(Dispatchers.Main) {
            newsViewState.value = DetailNewsViewState.Error(error = isNetworkError.parseError())
        }
    }
}