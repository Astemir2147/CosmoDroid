package com.ilein.cosmodroid.feature_news_list.presentation

import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.feature_news_list.presentation.model.ErrorModel

fun Boolean.parseError(): ErrorModel =
    if (this) ErrorModel(
        R.string.network_error_title,
        R.string.network_error_description,
    ) else ErrorModel(
        R.string.loading_error_title,
        R.string.loading_error_description,
    )