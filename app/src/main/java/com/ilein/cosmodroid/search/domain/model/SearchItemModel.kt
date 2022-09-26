package com.ilein.cosmodroid.search.domain.model

import com.ilein.cosmodroid.search.data.model.EnumSearchItems

data class SearchItemModel(
    val id: Int?,
    val idStr: String,
    val title: String,
    val description: String?,
    val imgUrl: String,
    val type: EnumSearchItems
)