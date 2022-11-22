package com.ilein.cosmodroid.search.domain

sealed class ResultState<out R> {
    data class Success<out S>(val data: S) : ResultState<S>()
    data class Error(val errorData: Boolean) : ResultState<Nothing>()
}

val <S>ResultState<S>.data: S?
    get() = (this as? ResultState.Success)?.data

val <S>ResultState<S>.errorData: Boolean?
    get() = (this as? ResultState.Error)?.errorData