package com.ilein.cosmodroid.search.domain

import com.ilein.cosmodroid.search.data.model.SearchResultAgencyByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel

interface Interactor {

    suspend fun getEventsItems(): List<SearchResultModel>

    suspend fun getLaunchesItems(): List<SearchResultLaunchModel>

    suspend fun getAstronautsItems(): List<SearchResultAstronautModel>

    suspend fun getAgenciesItems(): List<SearchResultModel>

    suspend fun getAgencyById(id: Int): SearchResultAgencyByIdModel

    suspend fun getSpaceStationsItems(): List<SearchResultModel>
}