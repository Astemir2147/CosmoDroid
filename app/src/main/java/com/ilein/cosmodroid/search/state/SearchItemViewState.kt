package com.ilein.cosmodroid.search.state

import com.ilein.cosmodroid.search.domain.model.SearchItemDetailModel
import com.ilein.cosmodroid.search.presentation.ErrorModel

sealed class SearchItemViewState {
    object Shimmer : SearchItemViewState()

    data class Error(val error: ErrorModel) : SearchItemViewState()
    data class Content(var searchItem: SearchItemDetailModel) : SearchItemViewState()
}