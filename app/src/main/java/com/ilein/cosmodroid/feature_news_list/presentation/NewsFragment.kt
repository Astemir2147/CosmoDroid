package com.ilein.cosmodroid.feature_news_list.presentation

import com.ilein.cosmodroid.feature_news_list.presentation.adapter.NewsAdapter
import com.ilein.cosmodroid.databinding.FragmentNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.ilein.cosmodroid.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.ilein.cosmodroid.ViewBindingFragment
import com.ilein.cosmodroid.common.modalBottomSheet.presentation.ModalBottomSheet
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.state.NewsViewState

class NewsFragment: ViewBindingFragment<FragmentNewsBinding>() {
    private lateinit var newsAdapter: NewsAdapter
    private val newsViewModel by viewModel<NewsViewModel>()

    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNewsBinding =
        FragmentNewsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        withSafeBinding {
            with(newsViewModel) {
                contentState.observe(viewLifecycleOwner, ::handleNewsState)
                newsViewModel.getNewsList()
                menuDrop.setText(ALL_TYPE_NAME)
            }
            newsAdapter = NewsAdapter(
                showBottomSheet = ::showBottomSheet,
                showDetailNews = ::showDetailNews
            )
            newsRecyclerView.adapter = newsAdapter

            val adapter =
                ArrayAdapter(requireContext(), R.layout.list_item, FilterTypes.typeNames())
            menuDrop.setAdapter(adapter)
            menuDrop.setOnItemClickListener { _, _, position, _ ->
                adapter.getItem(position)?.let {
                    if (it == ALL_TYPE_NAME){
                        newsViewModel.getNewsList()
                    } else {
                        newsViewModel.getNewsListByType(FilterTypes.findTypeByName(it).id)
                    }
                }
            }
        }
    }

    private fun handleNewsState(newsState: NewsViewState) {
        layoutHandle(newsState)
        when (newsState) {
            is NewsViewState.Shimmer -> nonNullBinding.shimmer.startShimmer()
            is NewsViewState.Content -> newsState.content()
            is NewsViewState.Error -> newsState.error()
        }
    }

    private fun layoutHandle(state: NewsViewState) {
        withSafeBinding {
            shimmer.stopShimmer()
            shimmer.isVisible = state is NewsViewState.Shimmer
            typeOfNewsMenu.isVisible = state is NewsViewState.Content
            newsRecyclerView.isVisible = state is NewsViewState.Content
            errorLayout.root.isVisible = state is NewsViewState.Error
        }
    }

    private fun NewsViewState.Content.content() {
        newsAdapter.setNewsList(list = newsList)
        nonNullBinding.newsRecyclerView.adapter = newsAdapter
    }

    private fun NewsViewState.Error.error() {
        with(nonNullBinding.errorLayout) {
            btnErrorTryAgain.setOnClickListener { onTryAction() }
            textErrorTitle.setText(error.title)
            textErrorDescription.setText(error.description)
        }
    }

    private fun onTryAction() {
        val currentType = nonNullBinding.menuDrop.text.toString()
        if (currentType == ALL_TYPE_NAME) {
            newsViewModel.getNewsList()
        } else {
            newsViewModel.getNewsListByType(FilterTypes.findTypeByName(currentType).id)
        }
    }

    private fun showDetailNews(newsItem: NewsItem) {
        findNavController().navigate(
            R.id.action_newsFragment_to_detailNewsFragment,
            bundleOf(NEWS_ITEM to newsItem)
        )
    }

    private fun showBottomSheet(newsItem: NewsItem) {
        val modalBottomSheet = ModalBottomSheet.newInstance(newsItem)
        modalBottomSheet.show(parentFragmentManager, ModalBottomSheet.TAG)
    }

    enum class FilterTypes(val typeName: String, val id: Int) {
        ALL(ALL_TYPE_NAME, 1),
        DOCKING(DOCKING_TYPE_NAME, 2),
        SPACECRAFT_EVENT(SPACECRAFT_EVENT_TYPE_NAME, 6),
        MOON_LANDING(MOON_LANDING_TYPE_NAME, 7);

        companion object {
            fun findTypeByName(typeName: String) = when (typeName) {
                ALL_TYPE_NAME -> ALL
                DOCKING_TYPE_NAME -> DOCKING
                SPACECRAFT_EVENT_TYPE_NAME -> SPACECRAFT_EVENT
                MOON_LANDING_TYPE_NAME -> MOON_LANDING
                else -> error("Unable to match $typeName with FilterType")
            }

            fun typeNames(): List<String> = values().map { it.typeName }
        }
    }

    companion object {
        private const val ALL_TYPE_NAME = "All type"
        private const val DOCKING_TYPE_NAME = "Docking"
        private const val SPACECRAFT_EVENT_TYPE_NAME = "Spacecraft Event"
        private const val MOON_LANDING_TYPE_NAME = "Moon Landing"
        private const val NEWS_ITEM = "newsItem"
    }
}