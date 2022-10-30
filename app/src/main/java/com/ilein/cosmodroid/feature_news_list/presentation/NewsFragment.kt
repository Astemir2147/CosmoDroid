package com.ilein.cosmodroid.feature_news_list.presentation

import com.ilein.cosmodroid.feature_news_list.presentation.adapter.NewsAdapter
import com.ilein.cosmodroid.databinding.FragmentNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.fragment.app.Fragment
import com.ilein.cosmodroid.R
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.state.NewsViewState

class NewsFragment : Fragment(R.layout.fragment_news) {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val newsViewModel by viewModel<NewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        with(newsViewModel) {
            contentState.observe(viewLifecycleOwner, ::handleNewsState)
            getNewsList()
        }
        newsAdapter = NewsAdapter(
            showBottomSheet = { showBottomSheet(it) },
            showDetailNews = { showDetailNews(it) }
        )
        binding.newsRecyclerView.adapter = newsAdapter
    }

    private fun handleNewsState(newsState: NewsViewState) {
        refresh(newsState)
        when (newsState) {
            is NewsViewState.Shimmer -> newsState.shimmer()
            is NewsViewState.Content -> newsState.content()
            is NewsViewState.Error -> newsState.error()
        }
    }

    private fun refresh(state: NewsViewState) {
        with(binding) {
            shimmer.stopShimmer()
            shimmer.isVisible = state is NewsViewState.Shimmer
            newsRecyclerView.isVisible = state is NewsViewState.Content
            errorLayout.isVisible = state is NewsViewState.Error
        }
    }

    private fun NewsViewState.Content.content() {
        newsAdapter.setNewsList(list = newsList)
        binding.newsRecyclerView.adapter = newsAdapter
    }

    private fun NewsViewState.Error.error() {
        with(binding) {
            btnErrorTryAgain.setOnClickListener { getOnTryAction() }
            textErrorTitle.setText(error.title)
            textErrorDescription.setText(error.description)
        }
    }

    private fun NewsViewState.Shimmer.shimmer() {
        binding.shimmer.startShimmer()
    }

    private fun getOnTryAction() {
        return newsViewModel.getNewsList()
    }

    private fun showDetailNews(newsItem: NewsItem) {
        arguments = Bundle().apply {
            putInt(ARG_PARAM_ID, newsItem.id)
            putString(ARG_PARAM_DATE, newsItem.date)
            putString(ARG_PARAM_TYPE, newsItem.type)
            putString(ARG_PARAM_DESCRIPTION, newsItem.description)
            putString(ARG_PARAM_IMAGE, newsItem.featureImage)
            putString(ARG_PARAM_NAME, newsItem.name)
        }
        findNavController().navigate(R.id.action_newsFragment_to_detailNewsFragment,arguments)
    }

    private fun showBottomSheet(newsItem: NewsItem) {
        val modalBottomSheet = ModalBottomSheet.newInstance(newsItem)
        modalBottomSheet.show(parentFragmentManager,ModalBottomSheet.TAG)
    }

    private companion object {
        private const val ARG_PARAM_ID = "paramIdOfNews"
        private const val ARG_PARAM_DATE = "paramDateOfNews"
        private const val ARG_PARAM_TYPE = "paramTypeOfNews"
        private const val ARG_PARAM_DESCRIPTION = "paramPreviewOfNews"
        private const val ARG_PARAM_IMAGE = "paramImageOfNews"
        private const val ARG_PARAM_NAME = "paramNameOfNews"
    }
}

