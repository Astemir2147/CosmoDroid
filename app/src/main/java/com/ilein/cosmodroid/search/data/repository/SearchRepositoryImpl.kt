package com.ilein.cosmodroid.search.data.repository

import com.ilein.cosmodroid.core.api.ApiRequest
import com.ilein.cosmodroid.search.data.model.SearchResultAgencyByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultEventByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultsModel
import com.ilein.cosmodroid.search.domain.SearchRepository
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.data.model.SearchResultSpaceStationByIdModel

class SearchRepositoryImpl(private val apiRequest: ApiRequest): SearchRepository {

    override suspend fun getEventsItems(searchQuery: String): SearchResultsModel<SearchResultModel> {
        return apiRequest.getSearchEvents(searchQuery)
    }

    override suspend fun getEventsItemById(id: Int): SearchResultEventByIdModel {
        return apiRequest.getEventById(id)
    }

    override suspend fun getLaunchesItems(searchQuery: String): SearchResultsModel<SearchResultLaunchModel> {
        return apiRequest.getSearchLaunches(searchQuery)
    }

    override suspend fun getLaunchItemById(id: String): SearchResultLaunchByIdModel {
        return apiRequest.getLaunchById(id)
    }

    override suspend fun getAstronautsItems(searchQuery: String): SearchResultsModel<SearchResultAstronautModel> {
        return apiRequest.getSearchAstronauts(searchQuery)
    }

    override suspend fun getAstronautItemById(id: Int): SearchResultAstronautByIdModel {
        return apiRequest.getAstronautById(id)
    }

    override suspend fun getAgenciesItems(searchQuery: String): SearchResultsModel<SearchResultModel> {
        return apiRequest.getSearchAgencies(searchQuery)
    }

    override suspend fun getAgencyItemById(id: Int): SearchResultAgencyByIdModel {
        return apiRequest.getAgencyById(id)
    }

    override suspend fun getSpaceStationsItems(searchQuery: String): SearchResultsModel<SearchResultModel> {
        return apiRequest.getSearchSpaceStations(searchQuery)
    }

    override suspend fun getSpaceStationsById(id: Int): SearchResultSpaceStationByIdModel {
        return apiRequest.getSpaceStationById(id)
    }
}