package com.ilein.cosmodroid.search.data.repository

import com.ilein.cosmodroid.search.data.api.RetrofitInstance
import com.ilein.cosmodroid.search.data.model.SearchResultsModel
import com.ilein.cosmodroid.search.domain.SearchRepository

class SearchRepositoryImpl: SearchRepository {
    override suspend fun getSearchItemsList(): SearchResultsModel {
        return RetrofitInstance.apiRequest.getAstronauts()
    }
}