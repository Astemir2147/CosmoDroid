package com.ilein.cosmodroid.feature_favourites_news.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.databinding.FragmentFavouritesNewsBinding
import com.ilein.cosmodroid.feature_favourites_news.presentation.adapter.FavoritesAdapter
import com.ilein.cosmodroid.feature_favourites_news.presentation.model.DbNewsItem
import com.ilein.cosmodroid.feature_favourites_news.presentation.model.dbNewsToNewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.ModalBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesNewsFragment: Fragment(R.layout.fragment_favourites_news) {
    private lateinit var binding: FragmentFavouritesNewsBinding
    private lateinit var adapter: FavoritesAdapter
    private val favouriteViewModel by viewModel<FavouritesNewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouritesNewsBinding.bind(view)
        adapter = FavoritesAdapter(
            showBottomSheet = { showBottomSheet(it) },
            showDetailNews = { showDetailNews(it) }
        )
        favouriteViewModel.getNewsList()
        favouriteViewModel.contentState.observe(viewLifecycleOwner, ::data)
    }

    fun data(state: FavoritesViewState) {
        when (state) {
            is FavoritesViewState.Content -> state.content()
            is FavoritesViewState.EmptyDatabase -> state.emptyDb()
        }
    }

    private fun FavoritesViewState.Content.content() {
        adapter.setNewsList(list = newsList)
        binding.favouritesNewsRecyclerView.adapter = adapter
    }

    private fun FavoritesViewState.EmptyDatabase.emptyDb() {
        binding.emptyDbMessage.isVisible = true
    }

    private fun showDetailNews(newsItem: DbNewsItem) {
        arguments = Bundle().apply {
            putInt(ARG_PARAM_ID, newsItem.id)
            putString(ARG_PARAM_DATE, newsItem.date)
            putString(ARG_PARAM_TYPE, newsItem.type)
            putString(ARG_PARAM_DESCRIPTION, newsItem.description)
            putString(ARG_PARAM_IMAGE, newsItem.featureImage)
            putString(ARG_PARAM_NAME, newsItem.name)
        }
        findNavController().navigate(R.id.detailNewsLayout, arguments)
    }

    private fun showBottomSheet(newsItem: DbNewsItem) {
        val modalBottomSheet = ModalBottomSheet.newInstance(newsItem = newsItem.dbNewsToNewsItem())
        modalBottomSheet.show(parentFragmentManager, ModalBottomSheet.TAG)
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