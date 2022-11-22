package com.ilein.cosmodroid.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilein.cosmodroid.search.data.model.EnumSearchItems
import com.ilein.cosmodroid.search.data.model.EnumSearchItems.*
import com.ilein.cosmodroid.search.data.model.toSearchItem
import com.ilein.cosmodroid.search.domain.Interactor
import com.ilein.cosmodroid.search.domain.ResultState
import com.ilein.cosmodroid.search.domain.model.SearchItemDetailModel
import com.ilein.cosmodroid.search.state.SearchItemViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchItemDetailViewModel(private val searchInteractor: Interactor) : ViewModel() {
    private var searchItemMutableLiveData = MutableLiveData<SearchItemViewState>()
    val searchItemLiveData: LiveData<SearchItemViewState>
        get() = searchItemMutableLiveData

    fun loadSearchItem(searchType: Int, id: Int?, idStr: String?) {
        searchItemMutableLiveData.value = SearchItemViewState.Shimmer
        val enumSearchItem: EnumSearchItems = EnumSearchItems.getById(searchType)!!

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (enumSearchItem) {
                    EVENTS -> {
                        id?.let {
                            val searchItem = searchInteractor.getEventById(it)
                            when (searchItem) {
                                is ResultState.Success -> {
                                    dispatcherSuccess(searchItem.data.toSearchItem())
                                }
                                is ResultState.Error -> {
                                    dispatcherError(searchItem)
                                }
                            }
                        }
                    }
                    AGENCIES -> {
                        id?.let {
                            val searchItem = searchInteractor.getAgencyById(it)
                            when (searchItem) {
                                is ResultState.Success -> {
                                    dispatcherSuccess(searchItem.data.toSearchItem())
                                }
                                is ResultState.Error -> {
                                    dispatcherError(searchItem)
                                }
                            }
                        }
                    }
                    ASTRONAUTS -> {
                        id?.let {
                            val searchItem = searchInteractor.getAstronautById(it)
                            when (searchItem) {
                                is ResultState.Success -> {
                                    dispatcherSuccess(searchItem.data.toSearchItem())
                                }
                                is ResultState.Error -> {
                                    dispatcherError(searchItem)
                                }
                            }
                        }
                    }
                    STATIONS -> {
                        id?.let {
                            val searchItem = searchInteractor.getSpaceStationById(it)
                            when (searchItem) {
                                is ResultState.Success -> {
                                    dispatcherSuccess(searchItem.data.toSearchItem())
                                }
                                is ResultState.Error -> {
                                    dispatcherError(searchItem)
                                }
                            }
                        }
                    }
                    LAUNCHES -> {
                        idStr?.let {
                            val searchItem = searchInteractor.getLaunchById(it)
                            when (searchItem) {
                                is ResultState.Success -> {
                                    dispatcherSuccess(searchItem.data.toSearchItem())
                                }
                                is ResultState.Error -> {
                                    dispatcherError(searchItem)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun dispatcherSuccess(item: SearchItemDetailModel) {
        withContext(Dispatchers.Main) {
            searchItemMutableLiveData.postValue(SearchItemViewState.Content(searchItem = item))
        }
    }

    private suspend fun dispatcherError(resultState: ResultState<Error>) {
        withContext(Dispatchers.Main) {
            searchItemMutableLiveData.value =
                SearchItemViewState.Error(error = (resultState as ResultState.Error).parseError())
        }
    }
}