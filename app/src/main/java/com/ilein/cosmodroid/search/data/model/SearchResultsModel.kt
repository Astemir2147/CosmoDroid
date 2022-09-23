package com.ilein.cosmodroid.search.data.model

data class SearchResultsModel (
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<SearchResultModel>
)