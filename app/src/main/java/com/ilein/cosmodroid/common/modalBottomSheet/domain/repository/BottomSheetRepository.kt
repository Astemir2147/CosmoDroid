package com.ilein.cosmodroid.common.modalBottomSheet.domain.repository

import com.ilein.cosmodroid.common.modalBottomSheet.domain.model.NewsModelBS

/**
 * Интерфейс BottomSheetRepository
 *
 * @author Astemir Zashaev on 2022-11-8
 */
interface BottomSheetRepository {

    /** Проверяет содержится ли выбранный айтем с [newsId] в базе данных */
    suspend fun checkIsDataExists(newsId: Int): Boolean

    /** Добовляет [newsModel] в базу данных */
    suspend fun addToFavourite(newsModel: NewsModelBS)

    /** Удаляет из закладок выбранный айтем по [newsId] */
    suspend fun deleteFromFavouriteById(newsId: Int)
}