package com.ilein.cosmodroid.common.modalBottomSheet.domain.interactor

import com.ilein.cosmodroid.common.modalBottomSheet.domain.model.NewsModelBS

interface BottomSheetInteractor {

    /** Добовляет [newsModel] в базу данных */
    suspend fun addToFavourite(newsModel: NewsModelBS)

    /** Проверяет содержится ли выбранный айтем с [newsId] в базе данных */
    suspend fun checkIsDataExists(newsId: Int)

    /** Удаляет из закладок выбранный айтем по [newsId] */
    suspend fun deleteFromFavouriteById(newsId: Int)
}