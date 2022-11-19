package com.ilein.cosmodroid.feature_news_list.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.core.database.dao.NewsDao
import com.ilein.cosmodroid.databinding.FragmentModalBottomSheetBinding
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.model.toNewsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.get

class ModalBottomSheet: BottomSheetDialogFragment() {
    private var binding: FragmentModalBottomSheetBinding? = null
    private lateinit var database: NewsDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_modal_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = get<NewsDao>()
        binding = FragmentModalBottomSheetBinding.bind(view)
        binding?.addToFavourite?.setOnClickListener { addToFavourite() }
        binding?.shareAnNews?.setOnClickListener { shareNews(getNewsItem()) }
        binding?.openOriginalNewsPage?.setOnClickListener { openUrlInBrowser(getNewsItem().url) }
    }

    private fun addToFavourite() {
        lifecycleScope.launchWhenResumed {
            withContext(Dispatchers.IO) {
                database.setFavouritesNews(getNewsItem().toNewsEntity())
            }
        }
    }

    private fun shareNews(newsItem: NewsItem) {
        val typeShare = "text/plain"
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, newsItem.url)
            type = typeShare
        }
        startActivity(shareIntent)
    }

    private fun openUrlInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun getNewsItem(): NewsItem =
        requireArguments().getSerializable(NEWS_ITEM) as NewsItem

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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