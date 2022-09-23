package com.ilein.cosmodroid.search.domain

import com.ilein.cosmodroid.search.data.model.SearchResultModel

interface Interactor {
    suspend fun getSearchItems(): List<SearchResultModel>
}