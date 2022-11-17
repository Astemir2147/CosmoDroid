package com.ilein.cosmodroid.search.domain

import com.ilein.cosmodroid.search.data.model.SearchResultAgencyByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultEventByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.data.model.SearchResultSpaceStationByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultsModel

interface SearchRepository {

    suspend fun getEventsItems(): SearchResultsModel<SearchResultModel>

    suspend fun getEventsItemById(id: Int): SearchResultEventByIdModel

    suspend fun getLaunchesItems(): SearchResultsModel<SearchResultLaunchModel>

    suspend fun getLaunchItemById(id: String): SearchResultLaunchByIdModel

    suspend fun getAstronautsItems(): SearchResultsModel<SearchResultAstronautModel>

    suspend fun getAstronautItemById(id: Int): SearchResultAstronautByIdModel

    suspend fun getAgenciesItems(): SearchResultsModel<SearchResultModel>

    suspend fun getAgencyItemById(id: Int): SearchResultAgencyByIdModel

    suspend fun getSpaceStationsItems(): SearchResultsModel<SearchResultModel>

    suspend fun getSpaceStationsById(id: Int): SearchResultSpaceStationByIdModel
}