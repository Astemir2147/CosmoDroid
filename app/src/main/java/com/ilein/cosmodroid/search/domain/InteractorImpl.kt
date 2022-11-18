package com.ilein.cosmodroid.search.domain

import com.ilein.cosmodroid.search.data.model.SearchResultAgencyByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultEventByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.data.model.SearchResultSpaceStationByIdModel

class InteractorImpl(private val searchRepository: SearchRepository): Interactor {

    override suspend fun getEventsItems(searchQuery: String): List<SearchResultModel> {
        return searchRepository.getEventsItems(searchQuery).results
    }

    override suspend fun getEventById(id: Int): SearchResultEventByIdModel {
        return searchRepository.getEventsItemById(id)
    }

    override suspend fun getLaunchesItems(searchQuery: String): List<SearchResultLaunchModel> {
        return searchRepository.getLaunchesItems(searchQuery).results
    }

    override suspend fun getLaunchById(id: String): SearchResultLaunchByIdModel {
        return searchRepository.getLaunchItemById(id)
    }

    override suspend fun getAstronautsItems(searchQuery: String): List<SearchResultAstronautModel> {
        return searchRepository.getAstronautsItems(searchQuery).results
    }

    override suspend fun getAstronautById(id: Int): SearchResultAstronautByIdModel {
        return searchRepository.getAstronautItemById(id)
    }

    override suspend fun getAgenciesItems(searchQuery: String): List<SearchResultModel> {
        return searchRepository.getAgenciesItems(searchQuery).results
    }

    override suspend fun getAgencyById(id: Int): SearchResultAgencyByIdModel {
        return searchRepository.getAgencyItemById(id)
    }

    override suspend fun getSpaceStationsItems(searchQuery: String): List<SearchResultModel> {
        return searchRepository.getSpaceStationsItems(searchQuery).results
    }

    override suspend fun getSpaceStationById(id: Int): SearchResultSpaceStationByIdModel {
        return searchRepository.getSpaceStationsById(id)
    }
}