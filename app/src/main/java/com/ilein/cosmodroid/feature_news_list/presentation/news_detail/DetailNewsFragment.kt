package com.ilein.cosmodroid.feature_news_list.presentation.news_detail

import android.view.View
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.databinding.FragmentDetailNewsBinding
import com.ilein.cosmodroid.feature_news_list.presentation.state.DetailNewsViewState
import com.ilein.cosmodroid.feature_news_list.presentation.model.DetailNewsItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import coil.load

class DetailNewsFragment: Fragment(R.layout.fragment_detail_news) {
    private lateinit var binding: FragmentDetailNewsBinding
    private val newsViewModel by viewModel<DetailNewsViewModel>()
    private var newsId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsId = requireArguments().getInt(NEWS_ID)
        newsViewModel.getNews(newsId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.contentState.observe(viewLifecycleOwner, ::handleNewsState)
        binding = FragmentDetailNewsBinding.bind(view)
        binding.topAppBar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun handleNewsState(newsState: DetailNewsViewState) {
        refresh(newsState)
        when (newsState) {
            is DetailNewsViewState.Content -> setDate(newsState.news)
            is DetailNewsViewState.Error -> newsState.handle()
            else -> {}
        }
    }

    private fun refresh(state: DetailNewsViewState) {
        with(binding) {
            detailNewsLayout.isVisible = state is DetailNewsViewState.Content
            detailErrorLayout.isVisible = state is DetailNewsViewState.Error
        }
    }

    private fun setDate(news: DetailNewsItem) {
        with(binding) {
            nameOfNews.text = news.name
            dateOfNewsDetail.text = news.date
            typeOfNewsDetail.text = news.type.name
            newsContent.text = news.description
            imageOfDetailNews.load(news.featureImage)
        }
    }

    private fun DetailNewsViewState.Error.handle() {
        with(binding) {
            btnErrorTryAgain.setOnClickListener { getOnTryAction(newsId) }
            textErrorTitle.setText(error.title)
            textErrorDescription.setText(error.description)
        }
    }

    private fun getOnTryAction(id: Int) {
        return newsViewModel.getNews(id)
    }

    private companion object {
        const val NEWS_ID = "NEWS_ID"
    }
}