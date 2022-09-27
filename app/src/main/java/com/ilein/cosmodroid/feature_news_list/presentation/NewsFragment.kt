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
            getNewsList()
            state.observe(viewLifecycleOwner, ::handleNewsState)
        }

    }

    private fun handleNewsState(newsState: NewsViewState) {
        when (newsState) {
            is NewsViewState.Content -> {
                newsAdapter = NewsAdapter(newsState.newsList)
                binding.newsRecyclerView.adapter = newsAdapter
            }
        }
    }
}

