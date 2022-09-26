package com.ilein.cosmodroid.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilein.cosmodroid.search.data.model.EnumSearchItems
import com.ilein.cosmodroid.search.data.model.toListSearchItem
import com.ilein.cosmodroid.search.domain.Interactor
import com.ilein.cosmodroid.search.domain.model.SearchItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val searchInteractor: Interactor) : ViewModel() {

    private var searchListMutableLiveData = MutableLiveData<List<SearchItemModel>>()
    val searchListLiveData: LiveData<List<SearchItemModel>>
        get() = searchListMutableLiveData

    fun loadSearchItemsList(searchType: Int) {
        val enumSearchItem: EnumSearchItems? = EnumSearchItems.getById(searchType)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val searchItems = ArrayList<SearchItemModel>()
                when(enumSearchItem) {
                    EnumSearchItems.EVENTS -> {
                        searchItems.addAll(searchInteractor.getEventsItems().toListSearchItem(enumSearchItem))
                    }
                    EnumSearchItems.AGENCIES -> {
                        searchItems.addAll(searchInteractor.getAgenciesItems().toListSearchItem(enumSearchItem))
                    }
                    EnumSearchItems.ASTRONAUTS -> {
                        searchItems.addAll(searchInteractor.getAstronautsItems().toListSearchItem(enumSearchItem))
                    }
                    EnumSearchItems.STATIONS -> {
                        searchItems.addAll(searchInteractor.getSpaceStationsItems().toListSearchItem(enumSearchItem))
                    }
                    EnumSearchItems.LAUNCHES -> {
                        searchItems.addAll(searchInteractor.getLaunchesItems().toListSearchItem(enumSearchItem))
                    }
                    else -> {}
                }
                withContext(Dispatchers.Main) {
                    searchListMutableLiveData.postValue(searchItems)
                }
            }
        }
    }
}