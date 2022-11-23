package com.ilein.cosmodroid.common.modalBottomSheet.di

import com.ilein.cosmodroid.common.modalBottomSheet.data.repository.BottomSheetRepositoryImpl
import com.ilein.cosmodroid.common.modalBottomSheet.domain.interactor.BottomSheetInteractor
import com.ilein.cosmodroid.common.modalBottomSheet.domain.interactor.BottomSheetInteractorImpl
import com.ilein.cosmodroid.common.modalBottomSheet.domain.repository.BottomSheetRepository
import com.ilein.cosmodroid.common.modalBottomSheet.presentation.BottomSheetViewModel
import com.ilein.cosmodroid.core.database.dao.NewsDao
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bottomSheetModule = module {
    factory<BottomSheetRepository> { BottomSheetRepositoryImpl(dao = get() as NewsDao) }
    factory<BottomSheetInteractor> { BottomSheetInteractorImpl(repository = get()) }
    viewModel { BottomSheetViewModel(repository = get()) }
}