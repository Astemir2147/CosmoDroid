package com.ilein.cosmodroid.feature_news_list.presentation

import com.ilein.cosmodroid.feature_news_list.domain.interactor.NewsInteractor
import com.ilein.cosmodroid.feature_news_list.domain.ResultState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(private val newsInteractor: NewsInteractor) : ViewModel() {
    val state = MutableLiveData<NewsViewState>()
    var contentState = NewsViewState.Content()
        set(value) {
            field = value
            state.value = field
        }

    fun getNewsList() {
        viewModelScope.launch {
            when (val news = newsInteractor.getNewsList()) {
                is ResultState.Success -> { contentState = NewsViewState.Content(news.data) }
                is ResultState.Error -> { NewsViewState.Error(Exception()) }
                else -> { ResultState.Loading }
            }
        }
    }

}