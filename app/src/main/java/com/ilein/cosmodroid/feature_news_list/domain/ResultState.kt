package com.ilein.cosmodroid.feature_news_list.domain

sealed class ResultState<out R>{
    data class Success<out S>(val data: S) : ResultState<S>()
    data class Error<out S>(val errorData: S) : ResultState<S>()
    object Loading : ResultState<List<Nothing>>()
}

val <S>ResultState<S>.data: S?
    get() = (this as? ResultState.Success)?.data

val <S>ResultState<S>.errorDate: S?
    get() = (this as? ResultState.Error)?.errorData