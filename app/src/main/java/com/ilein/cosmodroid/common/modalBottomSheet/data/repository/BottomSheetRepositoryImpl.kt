package com.ilein.cosmodroid.common.modalBottomSheet.data.repository

import com.ilein.cosmodroid.common.modalBottomSheet.data.model.toNewsEntity
import com.ilein.cosmodroid.common.modalBottomSheet.domain.model.NewsModelBS
import com.ilein.cosmodroid.common.modalBottomSheet.domain.repository.BottomSheetRepository
import com.ilein.cosmodroid.core.database.dao.NewsDao

/**
 * Реализация интерфейса [BottomSheetRepository]
 *
 * @author Astemir Zashaev on 2022-11-8
 */
class BottomSheetRepositoryImpl(private val dao: NewsDao): BottomSheetRepository {

    override suspend fun checkIsDataExists(newsId: Int): Boolean {
        return dao.isDateExists(id = newsId)
    }

    override suspend fun addToFavourite(newsModel: NewsModelBS) {
        dao.setFavouritesNews(newsEntity = newsModel.toNewsEntity())
    }

    override suspend fun deleteFromFavouriteById(newsId: Int) {
        dao.deleteById(newsId = newsId)
    }

}