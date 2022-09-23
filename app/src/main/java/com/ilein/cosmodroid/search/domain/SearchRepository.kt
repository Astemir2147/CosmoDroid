package com.ilein.cosmodroid.search.domain

import com.ilein.cosmodroid.search.data.model.SearchResultsModel

interface SearchRepository {
    suspend fun getSearchItemsList(): SearchResultsModel
}