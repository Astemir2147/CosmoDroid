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

    suspend fun getEventsItems(): List<SearchResultModel>

    suspend fun getEventById(id: Int): SearchResultEventByIdModel

    suspend fun getLaunchesItems(): List<SearchResultLaunchModel>

    suspend fun getLaunchById(id: String): SearchResultLaunchByIdModel

    suspend fun getAstronautsItems(): List<SearchResultAstronautModel>

    suspend fun getAstronautById(id: Int): SearchResultAstronautByIdModel

    suspend fun getAgenciesItems(): List<SearchResultModel>

    suspend fun getAgencyById(id: Int): SearchResultAgencyByIdModel

    suspend fun getSpaceStationsItems(): List<SearchResultModel>

    suspend fun getSpaceStationById(id: Int): SearchResultSpaceStationByIdModel
}