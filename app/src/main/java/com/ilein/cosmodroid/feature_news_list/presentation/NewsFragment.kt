package com.ilein.cosmodroid.feature_news_list.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.databinding.FragmentNewsBinding
import com.ilein.cosmodroid.feature_news_list.data.model.ResultNews
import com.ilein.cosmodroid.feature_news_list.presentation.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment_news) {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val newsViewModel by viewModel<NewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        setInstalledComponents()
    }

    private fun setInstalledComponents() {
        newsViewModel.getNewsList()
        newsViewModel.newsListLiveData.observe(viewLifecycleOwner, ::setComponents)
    }

    private fun setComponents(list: List<ResultNews>) {
        if (list.isNotEmpty()) {
            newsAdapter = NewsAdapter(list)
            binding.newsRecyclerView.adapter = newsAdapter
        }
    }
}

