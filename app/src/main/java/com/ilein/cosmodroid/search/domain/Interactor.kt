package com.ilein.cosmodroid.search.domain

import com.ilein.cosmodroid.search.data.model.SearchResultAgencyByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultEventByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.data.model.SearchResultSpaceStationByIdModel

interface Interactor {

    suspend fun getEventsItems(searchQuery: String): ResultState<List<SearchResultModel>>

    suspend fun getEventById(id: Int): ResultState<SearchResultEventByIdModel>

    suspend fun getLaunchesItems(searchQuery: String): ResultState<List<SearchResultLaunchModel>>

    suspend fun getLaunchById(id: String): ResultState<SearchResultLaunchByIdModel>

    suspend fun getAstronautsItems(searchQuery: String): ResultState<List<SearchResultAstronautModel>>

    suspend fun getAstronautById(id: Int): ResultState<SearchResultAstronautByIdModel>

    suspend fun getAgenciesItems(searchQuery: String): ResultState<List<SearchResultModel>>

    suspend fun getAgencyById(id: Int): ResultState<SearchResultAgencyByIdModel>

    suspend fun getSpaceStationsItems(searchQuery: String): ResultState<List<SearchResultModel>>

    suspend fun getSpaceStationById(id: Int): ResultState<SearchResultSpaceStationByIdModel>
}