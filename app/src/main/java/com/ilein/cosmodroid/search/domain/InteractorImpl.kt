package com.ilein.cosmodroid.search.domain

import com.ilein.cosmodroid.search.data.model.SearchResultModel

class InteractorImpl(private val searchRepository: SearchRepository): Interactor {
    override suspend fun getSearchItems(): List<SearchResultModel>{
        return searchRepository.getSearchItemsList().results
    }
}