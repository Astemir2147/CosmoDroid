package com.ilein.cosmodroid.search.domain

import com.ilein.cosmodroid.search.data.model.SearchResultAgencyByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultEventByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.data.model.SearchResultSpaceStationByIdModel

class InteractorImpl(private val searchRepository: SearchRepository) : Interactor {

    override suspend fun getEventsItems(searchQuery: String): ResultState<List<SearchResultModel>> =
        safeCall { searchRepository.getEventsItems(searchQuery).results }

    override suspend fun getEventById(id: Int): ResultState<SearchResultEventByIdModel> =
        safeCall { searchRepository.getEventsItemById(id) }

    override suspend fun getLaunchesItems(searchQuery: String): ResultState<List<SearchResultLaunchModel>> =
        safeCall { searchRepository.getLaunchesItems(searchQuery).results }

    override suspend fun getLaunchById(id: String): ResultState<SearchResultLaunchByIdModel> =
        safeCall { searchRepository.getLaunchItemById(id) }

    override suspend fun getAstronautsItems(searchQuery: String): ResultState<List<SearchResultAstronautModel>> =
        safeCall { searchRepository.getAstronautsItems(searchQuery).results }

    override suspend fun getAstronautById(id: Int): ResultState<SearchResultAstronautByIdModel> =
        safeCall { searchRepository.getAstronautItemById(id) }

    override suspend fun getAgenciesItems(searchQuery: String): ResultState<List<SearchResultModel>> =
        safeCall { searchRepository.getAgenciesItems(searchQuery).results }

    override suspend fun getAgencyById(id: Int): ResultState<SearchResultAgencyByIdModel> =
        safeCall { searchRepository.getAgencyItemById(id) }

    override suspend fun getSpaceStationsItems(searchQuery: String): ResultState<List<SearchResultModel>> =
        safeCall { searchRepository.getSpaceStationsItems(searchQuery).results }

    override suspend fun getSpaceStationById(id: Int): ResultState<SearchResultSpaceStationByIdModel> =
        safeCall { searchRepository.getSpaceStationsById(id) }
}