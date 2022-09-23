package com.ilein.cosmodroid.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val searchInteractor: Interactor) : ViewModel() {

    private var searchListMutableLiveData = MutableLiveData<List<SearchResultModel>>()
    val searchListLiveData: LiveData<List<SearchResultModel>>
        get() = searchListMutableLiveData

    fun getSearchItemsList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val searchItems = searchInteractor.getSearchItems()
                withContext(Dispatchers.Main) {
                    searchListMutableLiveData.postValue(searchItems)
                }
            }
        }
    }
}