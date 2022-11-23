package com.ilein.cosmodroid.feature_favourites_news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.ViewBindingFragment
import com.ilein.cosmodroid.databinding.FragmentFavouritesNewsBinding
import com.ilein.cosmodroid.feature_favourites_news.presentation.adapter.FavoritesAdapter
import com.ilein.cosmodroid.feature_favourites_news.presentation.model.DbNewsItem
import com.ilein.cosmodroid.feature_favourites_news.presentation.model.dbNewsToNewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.ModalBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesNewsFragment : ViewBindingFragment<FragmentFavouritesNewsBinding>() {
    private lateinit var adapter: FavoritesAdapter
    private val favouriteViewModel by viewModel<FavouritesNewsViewModel>()

    override val initBinding: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentFavouritesNewsBinding =
    FragmentFavouritesNewsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavoritesAdapter(
            showBottomSheet = { showBottomSheet(it) },
            showDetailNews = { showDetailNews(it) }
        )
        favouriteViewModel.getNewsList()
        favouriteViewModel.contentState.observe(viewLifecycleOwner, ::data)
    }

    fun data(state: FavoritesViewState) {
        layoutHandle(state)
        when (state) {
            is FavoritesViewState.Content -> state.content()
            else -> {}
        }
    }

    private fun FavoritesViewState.Content.content() {
        adapter.setNewsList(list = newsList)
        nonNullBinding.favouritesNewsRecyclerView.adapter = adapter
    }

    private fun layoutHandle(state: FavoritesViewState) {
        withSafeBinding {
           favouritesNewsRecyclerView.isVisible = state is FavoritesViewState.Content
           emptyDbMessage.isVisible = state is FavoritesViewState.EmptyDatabase
        }
    }

    private fun showDetailNews(newsItem: DbNewsItem) {
        arguments = Bundle().apply {
            putSerializable(NEWS_ITEM, newsItem.dbNewsToNewsItem())
        }
        findNavController().navigate(R.id.detailNewsLayout, arguments)
    }

    private fun showBottomSheet(newsItem: DbNewsItem) {
        val modalBottomSheet = ModalBottomSheet.newInstance(newsItem = newsItem.dbNewsToNewsItem())
        modalBottomSheet.show(parentFragmentManager, ModalBottomSheet.TAG)
    }

    private companion object {
        private const val NEWS_ITEM = "newsItem"
    }

}