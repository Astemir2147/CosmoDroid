package com.ilein.cosmodroid.search.domain

import com.ilein.cosmodroid.search.data.model.SearchResultAgencyByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.data.model.SearchResultsModel

interface SearchRepository {

    suspend fun getEventsItems(): SearchResultsModel<SearchResultModel>

    suspend fun getLaunchesItems(): SearchResultsModel<SearchResultLaunchModel>

    suspend fun getAstronautsItems(): SearchResultsModel<SearchResultAstronautModel>

    suspend fun getAgenciesItems(): SearchResultsModel<SearchResultModel>

    suspend fun getAgencyItemById(id: Int): SearchResultAgencyByIdModel

    suspend fun getSpaceStationsItems(): SearchResultsModel<SearchResultModel>
}