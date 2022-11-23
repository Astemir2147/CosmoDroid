package com.ilein.cosmodroid.common.modalBottomSheet.domain.interactor

import com.ilein.cosmodroid.common.modalBottomSheet.domain.model.NewsModelBS
import com.ilein.cosmodroid.common.modalBottomSheet.domain.repository.BottomSheetRepository

/**
 * Реализация интерфейса [BottomSheetInteractor]
 *
 * @author Astemir Zashaev on 2022-11-8
 */
class BottomSheetInteractorImpl(val repository: BottomSheetRepository):
    BottomSheetInteractor {

    override suspend fun addToFavourite(newsModel: NewsModelBS) {
        repository.addToFavourite(newsModel = newsModel)
    }

    override suspend fun checkIsDataExists(newsId: Int) {
        repository.checkIsDataExists(newsId = newsId)
    }

    override suspend fun deleteFromFavouriteById(newsId: Int) {
        repository.deleteFromFavouriteById(newsId = newsId)
    }

}