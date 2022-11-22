package com.ilein.cosmodroid.search.state

import com.ilein.cosmodroid.search.domain.model.SearchItemModel
import com.ilein.cosmodroid.search.presentation.ErrorModel

sealed class SearchViewState {
    object Shimmer : SearchViewState()

    data class Error(val error: ErrorModel) : SearchViewState()
    data class Content(var searchItemsList: List<SearchItemModel> = emptyList()) : SearchViewState()
}