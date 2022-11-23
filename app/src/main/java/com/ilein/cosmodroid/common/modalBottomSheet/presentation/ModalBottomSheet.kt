package com.ilein.cosmodroid.common.modalBottomSheet.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.common.modalBottomSheet.data.model.toNewsItemBS
import com.ilein.cosmodroid.common.modalBottomSheet.presentation.modal.NewsItemBS
import com.ilein.cosmodroid.core.database.dao.NewsDao
import com.ilein.cosmodroid.databinding.FragmentModalBottomSheetBinding
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.get

class ModalBottomSheet : BottomSheetDialogFragment() {
    private var binding: FragmentModalBottomSheetBinding? = null
    private val viewModel by viewModel<BottomSheetViewModel>()
    private lateinit var database: NewsDao
    private lateinit var newsItem: NewsItemBS

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_modal_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = get<NewsDao>()
        binding = FragmentModalBottomSheetBinding.bind(view)
        newsItem = (requireArguments().getSerializable(NEWS_ITEM) as NewsItem).toNewsItemBS()
        binding?.addToFavourite?.setOnClickListener { favouriteStateHandler() }
        binding?.shareAnNews?.setOnClickListener { shareNews(newsItem) }
        binding?.openOriginalNewsPage?.setOnClickListener { openUrlInBrowser(newsItem.url) }
        binding?.translateNews?.setOnClickListener {
            translateNewsInBrowser(
                newsText = newsItem.description,
                newsUrl = newsItem.url
            )
        }
        viewModel.checkIsDataExists(newsId = newsItem.id)
    }

    private fun favouriteStateHandler() {
        lifecycleScope.launchWhenResumed {
            if (viewModel.data.value == true) {
                iconChange(newsId = newsItem.id)
            } else {
                withContext(Dispatchers.IO) {
                    viewModel.addToFavourite(newsItem)
                }
            }
        }
        this.dismiss()
    }

    private fun iconChange(newsId: Int) {
        viewModel.deleteFromFavouriteById(newsId = newsId)
        binding?.addToFavourite?.setBackgroundResource(R.drawable.icon_news)
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

    private fun translateNewsInBrowser(newsUrl: String, newsText: String) {
        val originalNewsUrl = "\n $newsUrl"
        val translateWebSite = "https://translate.google.ru/?sl=en&tl=ru&text=$newsText&op=translate"
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse( translateWebSite + originalNewsUrl))

        this.dismiss()
        startActivity(browserIntent)
    }

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