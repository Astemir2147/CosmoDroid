package com.ilein.cosmodroid.feature_news_list.domain

sealed class ResultState<out R> {
    data class Success<out S>(val data: S): ResultState<S>()
    data class Error(val errorData: Boolean): ResultState<Nothing>()
}

val <S>ResultState<S>.data: S?
    get() = (this as? ResultState.Success)?.data

val <S>ResultState<S>.errorDate: Boolean?
    get() = (this as? ResultState.Error)?.errorData