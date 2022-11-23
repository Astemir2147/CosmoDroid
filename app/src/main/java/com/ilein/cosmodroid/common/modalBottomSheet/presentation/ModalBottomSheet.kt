package com.ilein.cosmodroid.common.modalBottomSheet.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.common.modalBottomSheet.data.model.toNewsItemBS
import com.ilein.cosmodroid.common.modalBottomSheet.presentation.modal.NewsItemBS
import com.ilein.cosmodroid.core.database.dao.NewsDao
import com.ilein.cosmodroid.databinding.FragmentModalBottomSheetBinding
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.get

class ModalBottomSheet : ViewBindingBottomSheet<FragmentModalBottomSheetBinding>() {
    private val viewModel by viewModel<BottomSheetViewModel>()
    private lateinit var database: NewsDao
    private lateinit var newsItem: NewsItemBS

    override val initBinding: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentModalBottomSheetBinding =
        FragmentModalBottomSheetBinding::inflate

    override fun onStart() {
        super.onStart()
        newsItem = (requireArguments().getSerializable(NEWS_ITEM) as NewsItem).toNewsItemBS()
        viewModel.checkIsDataExists(newsId = newsItem.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = get()
        viewModel.isItemInFavorite.observe(this, ::setupIcon)
        withSafeBinding {
            addToFavourite.setOnClickListener { favouriteStateHandler() }
            shareAnNews.setOnClickListener { shareNews(newsItem) }
            openOriginalNewsPage.setOnClickListener { openUrlInBrowser(newsItem.url) }
        }
    }

    private fun favouriteStateHandler() {
        val onItemActionHandler =
            if (parentFragment is OnItemRemovedHandler) parentFragment as OnItemRemovedHandler else null

        if (viewModel.isItemInFavorite.value == true) {
            viewModel.deleteFromFavouriteById(newsId = newsItem.id)
            onItemActionHandler?.onItemRemoved(newsItem.id)
        } else {
            viewModel.addToFavourite(newsItem)
        }
        dismiss()
    }

    private fun setupIcon(isFavorite: Boolean) {
        val (icon, text) = if (isFavorite) {
            R.drawable.ic_favorite_red to R.string.remove_favourite_text
        } else {
            R.drawable.icon_favorite_heart to R.string.add_favourite_text
        }
        nonNullBinding.addToFavourite.let {
            it.setIconTintResource(R.color.red)
            it.setIconResource(icon)
            it.setText(text)
        }
    }

    private fun shareNews(newsItem: NewsItemBS) {
        val typeShare = "text/plain"
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, newsItem.url)
            type = typeShare
        }
        startActivity(shareIntent)
        this.dismiss()
    }

    private fun openUrlInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.dismiss()
        startActivity(browserIntent)
    }

    companion object {
        const val TAG = "ModalBottomSheet"
        private const val NEWS_ITEM = "newsItem"

        @JvmStatic
        fun newInstance(newsItem: NewsItem) =
            ModalBottomSheet().apply {
                arguments = Bundle().apply {
                    putSerializable(NEWS_ITEM, newsItem)
                }
            }
    }
}