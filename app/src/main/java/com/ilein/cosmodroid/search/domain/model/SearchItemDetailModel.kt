package com.ilein.cosmodroid.search.domain.model

data class SearchItemDetailModel(
    val idStr: String,
    val title: String,
    val description: String?,
    val imgUrl: String?,
    val fullDescription: String?
)