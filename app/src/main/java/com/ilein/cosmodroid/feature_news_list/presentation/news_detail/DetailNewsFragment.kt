package com.ilein.cosmodroid.feature_news_list.presentation.news_detail

import android.content.Intent
import android.net.Uri
import coil.load
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ilein.cosmodroid.ViewBindingFragment
import com.ilein.cosmodroid.databinding.FragmentDetailNewsBinding
import com.ilein.cosmodroid.common.modalBottomSheet.presentation.ModalBottomSheet
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem

class DetailNewsFragment: ViewBindingFragment<FragmentDetailNewsBinding>() {
    private lateinit var newsItem: NewsItem

    override val initBinding: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentDetailNewsBinding =
        FragmentDetailNewsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withSafeBinding {
            goBackStack.setOnClickListener { findNavController().popBackStack() }
            detailSpinMenu.setOnClickListener { showBottomSheet(newsItem) }
            imageOfDetailNews.setOnClickListener { openUrlInBrowser(newsItem.videoUrl) }
        }
        newsItem = requireArguments().getSerializable(NEWS_ITEM) as NewsItem
        itemHandler(newsItem)
    }

    private fun showBottomSheet(newsItem: NewsItem) {
        val modalBottomSheet = ModalBottomSheet.newInstance(newsItem)
        modalBottomSheet.show(parentFragmentManager, ModalBottomSheet.TAG)
    }

    private fun openUrlInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun itemHandler(newsItem: NewsItem) {
        withSafeBinding {
            nameOfNews.text = newsItem.name
            dateOfNewsDetail.text = newsItem.name
            typeOfNewsDetail.text = newsItem.type
            newsContent.text = newsItem.description
            imageOfDetailNews.load(newsItem.featureImage)
        }
    }

    companion object {
        private const val NEWS_ITEM = "newsItem"
    }
}