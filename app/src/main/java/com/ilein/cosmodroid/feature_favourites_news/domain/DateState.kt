package com.ilein.cosmodroid.feature_favourites_news.domain

sealed class DateState<out R> {
    object Empty: DateState<Nothing>()
    data class Data<out S>(val data: S): DateState<S>()
}

val <S>DateState<S>.data: S?
    get() = (this as? DateState.Data)?.data