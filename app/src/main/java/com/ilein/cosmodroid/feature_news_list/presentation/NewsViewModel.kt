package com.ilein.cosmodroid.feature_news_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilein.cosmodroid.feature_news_list.data.model.ResultNews
import com.ilein.cosmodroid.feature_news_list.domain.interactor.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val newsInteractor: Interactor) : ViewModel() {

    private var newsListMutableLiveData = MutableLiveData<List<ResultNews>>()
    val newsListLiveData: LiveData<List<ResultNews>>
        get() = newsListMutableLiveData

    fun getNewsList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val news = newsInteractor.getNewsList()
                withContext(Dispatchers.Main) {
                    newsListMutableLiveData.postValue(news)
                }
            }
        }
    }

}