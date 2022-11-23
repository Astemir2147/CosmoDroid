package com.ilein.cosmodroid.feature_news_list.presentation

import com.ilein.cosmodroid.feature_news_list.presentation.adapter.NewsAdapter
import com.ilein.cosmodroid.databinding.FragmentNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.fragment.app.Fragment
import com.ilein.cosmodroid.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.ilein.cosmodroid.common.modalBottomSheet.presentation.ModalBottomSheet
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.state.NewsViewState

class NewsFragment: Fragment(R.layout.fragment_news) {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val newsViewModel by viewModel<NewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        with(newsViewModel) {
            contentState.observe(viewLifecycleOwner, ::handleNewsState)
            newsViewModel.getNewsList()
            binding.menuDrop.setText(ALL_TYPE)
        }
        newsAdapter = NewsAdapter(
            showBottomSheet = { showBottomSheet(it) },
            showDetailNews = { showDetailNews(it) }
        )
        binding.newsRecyclerView.adapter = newsAdapter

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, ITEMS)
        binding.menuDrop.setAdapter(adapter)
        binding.menuDrop.setOnItemClickListener { _, _, position, _ ->
            adapter.getItem(position)?.let { menuHandler(it) }
        }
    }

    private fun menuHandler(value: String) {
        when (value) {
            ALL_TYPE -> newsViewModel.getNewsList()
            DOCKING -> newsViewModel.getNewsListByType(DOCKING_ID)
            SPACECRAFT_EVENT -> newsViewModel.getNewsListByType(SPACECRAFT_EVENT_ID)
            MOON_LANDING -> newsViewModel.getNewsListByType(MOON_LANDING_ID)
        }
    }

    private fun handleNewsState(newsState: NewsViewState) {
        layoutHandle(newsState)
        when (newsState) {
            is NewsViewState.Shimmer -> newsState.shimmer()
            is NewsViewState.Content -> newsState.content()
            is NewsViewState.Error -> newsState.error()
        }
    }

    private fun layoutHandle(state: NewsViewState) {
        with(binding) {
            shimmer.stopShimmer()
            shimmer.isVisible = state is NewsViewState.Shimmer
            typeOfNewsMenu.isVisible = state is NewsViewState.Content
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
        binding.menuDrop.setText(ALL_TYPE)
        return newsViewModel.getNewsList()
    }

    private fun showDetailNews(newsItem: NewsItem) {
        arguments = Bundle().apply {
            putSerializable(NEWS_ITEM, newsItem)
        }
        findNavController().navigate(R.id.action_newsFragment_to_detailNewsFragment, arguments)
    }

    private fun showBottomSheet(newsItem: NewsItem) {
        val modalBottomSheet = ModalBottomSheet.newInstance(newsItem)
        modalBottomSheet.show(parentFragmentManager, ModalBottomSheet.TAG)
    }

    private companion object {
        private const val ALL_TYPE = "All type"
        private const val DOCKING = "Docking"
        private const val DOCKING_ID = 2
        private const val SPACECRAFT_EVENT = "Spacecraft Event"
        private const val SPACECRAFT_EVENT_ID = 6
        private const val MOON_LANDING = "Moon Landing"
        private const val MOON_LANDING_ID = 7
        private val ITEMS = listOf(ALL_TYPE, DOCKING, SPACECRAFT_EVENT, MOON_LANDING)
        private const val NEWS_ITEM = "newsItem"
    }
}