package com.ilein.cosmodroid.search.data.model

data class SearchResultsModel<T> (
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<T>
)