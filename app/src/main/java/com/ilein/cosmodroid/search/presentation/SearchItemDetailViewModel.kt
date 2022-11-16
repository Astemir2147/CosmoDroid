package com.ilein.cosmodroid.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilein.cosmodroid.search.data.model.EnumSearchItems
import com.ilein.cosmodroid.search.data.model.toSearchItem
import com.ilein.cosmodroid.search.domain.Interactor
import com.ilein.cosmodroid.search.domain.model.SearchItemDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchItemDetailViewModel(private val searchInteractor: Interactor) : ViewModel() {
    private var searchAgencyMutableLiveData = MutableLiveData<SearchItemDetailModel>()
    val searchAgencyLiveData: LiveData<SearchItemDetailModel>
        get() = searchAgencyMutableLiveData

    fun loadSearchItem(searchType: Int, id: Int) {
        val enumSearchItem: EnumSearchItems? = EnumSearchItems.getById(searchType)

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                when(enumSearchItem) {
                    EnumSearchItems.EVENTS -> {

                    }
                    EnumSearchItems.AGENCIES -> {
                        val searchItem = searchInteractor.getAgencyById(id).toSearchItem()
                        withContext(Dispatchers.Main) {
                            searchAgencyMutableLiveData.postValue(searchItem)
                        }
                    }
                    EnumSearchItems.ASTRONAUTS -> {

                    }
                    EnumSearchItems.STATIONS -> {

                    }
                    EnumSearchItems.LAUNCHES -> {

                    }
                    else -> {}
                }
            }
        }
    }
}