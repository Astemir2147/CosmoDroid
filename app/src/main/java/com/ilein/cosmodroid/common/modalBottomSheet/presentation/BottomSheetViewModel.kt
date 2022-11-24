package com.ilein.cosmodroid.common.modalBottomSheet.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilein.cosmodroid.common.modalBottomSheet.data.model.toNewsModelBS
import com.ilein.cosmodroid.common.modalBottomSheet.domain.repository.BottomSheetRepository
import com.ilein.cosmodroid.common.modalBottomSheet.presentation.modal.NewsItemBS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BottomSheetViewModel(private var repository: BottomSheetRepository) : ViewModel() {

    private val data: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isItemInFavorite: LiveData<Boolean>
        get() = data

    fun addToFavourite(newsItem: NewsItemBS) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToFavourite(newsModel = newsItem.toNewsModelBS())
        }
    }

    fun deleteFromFavouriteById(newsId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFromFavouriteById(newsId = newsId)
        }
    }

    fun checkIsDataExists(newsId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val isExistDb = repository.checkIsDataExists(newsId = newsId)
                withContext(Dispatchers.Main) {
                    data.value = isExistDb
                }
            }
        }
    }
}