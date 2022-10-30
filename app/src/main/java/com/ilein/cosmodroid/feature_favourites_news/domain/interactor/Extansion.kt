package com.ilein.cosmodroid.feature_favourites_news.domain.interactor

import com.ilein.cosmodroid.feature_favourites_news.domain.DateState

fun <T>safeDbRequest(action:List<T>): DateState<List<T>> {
    return if (action.isNotEmpty()){
        DateState.Data(action)
    }else{
        DateState.Empty
    }
}