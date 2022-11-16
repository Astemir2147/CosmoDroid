package com.ilein.cosmodroid.search.domain.model

import com.ilein.cosmodroid.search.data.model.EnumSearchItems

/**
 * SearchItemModel - элементы для поиска.
 * id - числовой ID (при наличии)
 * idStr - id.toString() или id для launch (т.к. там id приходит в виде uuid)
 */


data class SearchItemModel(
    val id: Int?,
    val idStr: String,
    val title: String,
    val description: String?,
    val imgUrl: String?,
    val type: EnumSearchItems
)