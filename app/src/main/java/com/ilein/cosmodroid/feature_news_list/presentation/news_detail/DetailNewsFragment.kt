package com.ilein.cosmodroid.feature_news_list.presentation.news_detail

import android.view.View
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.databinding.FragmentDetailNewsBinding
import com.ilein.cosmodroid.feature_news_list.presentation.state.DetailNewsViewState
import org.koin.androidx.viewmodel.ext.android.viewModel
import coil.load


class DetailNewsFragment : Fragment(R.layout.fragment_detail_news) {
    private lateinit var binding: FragmentDetailNewsBinding
    private val newsViewModel by viewModel<DetailNewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailNewsBinding.bind(view)
        binding.topAppBar.setNavigationOnClickListener { findNavController().popBackStack() }
        setDate()
    }

    private fun refresh(state: DetailNewsViewState) {
        with(binding) {
            detailNewsLayout.isVisible = state is DetailNewsViewState.Content
            detailErrorLayout.isVisible = state is DetailNewsViewState.Error
        }
    }

    private fun dateState(state: Boolean): Boolean {
        with(binding) {
            when (state) {
                true -> {
                    detailNewsLayout.isVisible = true
                    return true
                }
                false -> {
                    detailErrorLayout.isVisible = true
                    return false
                }
            }
        }
    }

    private fun setDate() {
        with(binding) {
            val id = requireArguments().getString(ARG_PARAM_ID)
            if (dateState(id.isNullOrEmpty())) {
                nameOfNews.text = requireArguments().getString(ARG_PARAM_NAME)
                dateOfNewsDetail.text = requireArguments().getString(ARG_PARAM_DATE)
                typeOfNewsDetail.text = requireArguments().getString(ARG_PARAM_TYPE)
                newsContent.text = requireArguments().getString(ARG_PARAM_DESCRIPTION)
                imageOfDetailNews.load(requireArguments().getString(ARG_PARAM_IMAGE))
            }

        }
    }

    private fun DetailNewsViewState.Error.handle() {
        with(binding) {
            btnErrorTryAgain.setOnClickListener {
                getOnTryAction(requireArguments().getInt(ARG_PARAM_ID))
            }
            textErrorTitle.setText(error.title)
            textErrorDescription.setText(error.description)
        }
    }

    private fun getOnTryAction(id: Int) {
        return newsViewModel.getNews(id)
    }

    companion object {
        private const val ARG_PARAM_ID = "paramIdOfNews"
        private const val ARG_PARAM_DATE = "paramDateOfNews"
        private const val ARG_PARAM_TYPE = "paramTypeOfNews"
        private const val ARG_PARAM_DESCRIPTION = "paramPreviewOfNews"
        private const val ARG_PARAM_IMAGE = "paramImageOfNews"
        private const val ARG_PARAM_NAME = "paramNameOfNews"
    }
}