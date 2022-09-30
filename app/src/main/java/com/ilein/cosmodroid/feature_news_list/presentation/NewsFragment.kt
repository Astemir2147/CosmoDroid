package com.ilein.cosmodroid.feature_news_list.presentation

import com.ilein.cosmodroid.feature_news_list.presentation.adapter.NewsAdapter
import com.ilein.cosmodroid.databinding.FragmentNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.fragment.app.Fragment
import com.ilein.cosmodroid.R
import android.os.Bundle
import android.view.View

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
        newsAdapter = NewsAdapter()
        binding.newsRecyclerView.adapter = newsAdapter
    }

    private fun handleNewsState(newsState: NewsViewState) {
        when (newsState) {
            is NewsViewState.Content -> newsState.handle()
            is NewsViewState.Error -> newsState.handle()
            else -> {}
        }
    }

    private fun NewsViewState.Content.handle() {
        newsAdapter.setNewsList(newsList)
        binding.newsRecyclerView.adapter = newsAdapter
    }

    private fun NewsViewState.Error.handle() {}

}

