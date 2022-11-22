package com.ilein.cosmodroid.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilein.cosmodroid.search.data.model.EnumSearchItems
import com.ilein.cosmodroid.search.data.model.toListSearchItem
import com.ilein.cosmodroid.search.domain.Interactor
import com.ilein.cosmodroid.search.domain.ResultState
import com.ilein.cosmodroid.search.domain.model.SearchItemModel
import com.ilein.cosmodroid.search.state.SearchViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val searchInteractor: Interactor) : ViewModel() {

    private val searchViewState = MutableLiveData<SearchViewState>()
    val contentState: LiveData<SearchViewState> get() = searchViewState

    fun loadSearchItemsList(searchType: Int, searchQuery: String) {
        val enumSearchItem: EnumSearchItems? = EnumSearchItems.getById(searchType)
        viewModelScope.launch {
            searchViewState.value = SearchViewState.Shimmer
            withContext(Dispatchers.IO){
                when(enumSearchItem) {
                    EnumSearchItems.EVENTS -> {
                        when (val searchItems = searchInteractor.getEventsItems(searchQuery)) {
                            is ResultState.Success -> { dispatcherSuccess(searchItems.data.toListSearchItem(enumSearchItem)) }
                            is ResultState.Error -> { dispatcherError(searchItems) }
                        }
                    }
                    EnumSearchItems.AGENCIES -> {
                        when (val searchItems = searchInteractor.getAgenciesItems(searchQuery)) {
                            is ResultState.Success -> { dispatcherSuccess(searchItems.data.toListSearchItem(enumSearchItem)) }
                            is ResultState.Error -> { dispatcherError(searchItems) }
                        }
                    }
                    EnumSearchItems.ASTRONAUTS -> {
                        when (val searchItems = searchInteractor.getAstronautsItems(searchQuery)) {
                            is ResultState.Success -> { dispatcherSuccess(searchItems.data.toListSearchItem(enumSearchItem)) }
                            is ResultState.Error -> { dispatcherError(searchItems) }
                        }
                    }
                    EnumSearchItems.STATIONS -> {
                        when (val searchItems = searchInteractor.getSpaceStationsItems(searchQuery)) {
                            is ResultState.Success -> { dispatcherSuccess(searchItems.data.toListSearchItem(enumSearchItem)) }
                            is ResultState.Error -> { dispatcherError(searchItems) }
                        }
                    }
                    EnumSearchItems.LAUNCHES -> {
                        when (val searchItems = searchInteractor.getLaunchesItems(searchQuery)) {
                            is ResultState.Success -> { dispatcherSuccess(searchItems.data.toListSearchItem(enumSearchItem)) }
                            is ResultState.Error -> { dispatcherError(searchItems) }
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    private suspend fun dispatcherSuccess(list: List<SearchItemModel>) {
        withContext(Dispatchers.Main) {
            searchViewState.postValue(SearchViewState.Content(searchItemsList = list))
        }
    }

    private suspend fun dispatcherError(resultState: ResultState<Error>) {
        withContext(Dispatchers.Main) {
            searchViewState.value = SearchViewState.Error(error = (resultState as ResultState.Error).parseError())
        }
    }
}