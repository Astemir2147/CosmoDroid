package com.ilein.cosmodroid.search.presentation

import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.search.domain.ResultState

fun ResultState.Error.parseError(): ErrorModel =
    if (this.errorData) ErrorModel(
        R.string.network_error_title,
        R.string.network_error_description,
    ) else ErrorModel(
        R.string.loading_error_title,
        R.string.loading_error_description,
    )