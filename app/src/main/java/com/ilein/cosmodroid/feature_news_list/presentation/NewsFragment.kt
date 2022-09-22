package com.ilein.cosmodroid.feature_news_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.databinding.FragmentNewsBinding
import com.ilein.cosmodroid.feature_news_list.data.model.ResultNews
import com.ilein.cosmodroid.feature_news_list.presentation.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment_news) {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsAdapter: NewsAdapter
    private val newsViewModel by viewModel<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

