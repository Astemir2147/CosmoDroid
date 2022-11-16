package com.ilein.cosmodroid.search.domain

import com.ilein.cosmodroid.search.data.model.SearchResultAgencyByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel

class InteractorImpl(private val searchRepository: SearchRepository): Interactor {

    override suspend fun getEventsItems(): List<SearchResultModel> {
        return searchRepository.getEventsItems().results
    }

    override suspend fun getLaunchesItems(): List<SearchResultLaunchModel> {
        return searchRepository.getLaunchesItems().results
    }

    override suspend fun getAstronautsItems(): List<SearchResultAstronautModel> {
        return searchRepository.getAstronautsItems().results
    }

    override suspend fun getAgenciesItems(): List<SearchResultModel> {
        return searchRepository.getAgenciesItems().results
    }

    override suspend fun getAgencyById(id: Int): SearchResultAgencyByIdModel {
        return searchRepository.getAgencyItemById(id)
    }

    override suspend fun getSpaceStationsItems(): List<SearchResultModel> {
        return searchRepository.getSpaceStationsItems().results
    }
}