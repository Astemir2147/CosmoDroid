package com.ilein.cosmodroid.common.modalBottomSheet.presentation.modal

data class NewsItemBS(
    val id: Int,
    val date: String,
    val description: String,
    val featureImage: String,
    val type: String,
    val name: String,
    val url: String,
    val videoUrl: String
)