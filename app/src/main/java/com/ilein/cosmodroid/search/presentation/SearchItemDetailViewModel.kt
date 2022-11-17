package com.ilein.cosmodroid.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilein.cosmodroid.search.data.model.EnumSearchItems
import com.ilein.cosmodroid.search.data.model.EnumSearchItems.*
import com.ilein.cosmodroid.search.data.model.toSearchItem
import com.ilein.cosmodroid.search.domain.Interactor
import com.ilein.cosmodroid.search.domain.model.SearchItemDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchItemDetailViewModel(private val searchInteractor: Interactor) : ViewModel() {
    private var searchItemMutableLiveData = MutableLiveData<SearchItemDetailModel>()
    val searchAgencyLiveData: LiveData<SearchItemDetailModel>
        get() = searchItemMutableLiveData

    fun loadSearchItem(searchType: Int, id: Int?, idStr: String?) {
        val enumSearchItem: EnumSearchItems = EnumSearchItems.getById(searchType)!!

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                when(enumSearchItem) {
                    EVENTS -> {
                        id?.let { val searchItem = searchInteractor.getEventById(it).toSearchItem()
                            withContext(Dispatchers.Main) {
                                searchItemMutableLiveData.postValue(searchItem)
                            }
                        }
                    }
                    AGENCIES -> {
                        id?.let {
                            val searchItem = searchInteractor.getAgencyById(it).toSearchItem()
                            withContext(Dispatchers.Main) {
                                searchItemMutableLiveData.postValue(searchItem)
                            }
                        }
                    }
                    ASTRONAUTS -> {
                        id?.let {
                            val searchItem = searchInteractor.getAstronautById(it).toSearchItem()
                            withContext(Dispatchers.Main) {
                                searchItemMutableLiveData.postValue(searchItem)
                            }
                        }
                    }
                    STATIONS -> {
                        id?.let {
                            val searchItem = searchInteractor.getSpaceStationById(it).toSearchItem()
                            withContext(Dispatchers.Main) {
                                searchItemMutableLiveData.postValue(searchItem)
                            }
                        }
                    }
                    LAUNCHES -> {
                        idStr?.let {
                            val searchItem = searchInteractor.getLaunchById(it).toSearchItem()
                            withContext(Dispatchers.Main) {
                                searchItemMutableLiveData.postValue(searchItem)
                            }
                        }
                    }
                }
            }
        }
    }
}