package com.ilein.cosmodroid.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilein.cosmodroid.databinding.SearchFragmentBinding
import com.ilein.cosmodroid.search.state.SearchViewState
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private lateinit var _binding: SearchFragmentBinding
    private val binding get() = _binding

    private val args: SearchFragmentArgs by navArgs()

    private val myViewModel by viewModel<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.title
        binding.tvSearchName.text = args.title
        myViewModel.loadSearchItemsList(args.id, "")

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                myViewModel.loadSearchItemsList(args.id, binding.etSearch.text.toString())
            }
            true
        }

        searchAdapter = SearchAdapter { typeId, id, idStr ->
            findNavController().navigate(
                SearchFragmentDirections.toSearchItemDetailFragmentWithParams(
                    typeId,
                    id,
                    idStr
                )
            )
        }

        binding.rvSearchResults.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
        myViewModel.contentState.observe(requireActivity(), ::handleSearchState)
        binding.rvSearchResults.adapter = searchAdapter
        myViewModel.loadSearchItemsList(args.id, "")
    }

    private fun handleSearchState(searchState: SearchViewState) {
        layoutHandle(searchState)
        when (searchState) {
            is SearchViewState.Shimmer -> searchState.shimmer()
            is SearchViewState.Content -> searchState.content()
            is SearchViewState.Error -> searchState.error()
        }
    }

    private fun SearchViewState.Content.content() {
        searchAdapter.setItems(newItems = searchItemsList)
        binding.rvSearchResults.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }

    private fun SearchViewState.Error.error() {
        with(binding) {
            btnErrorTryAgain.setOnClickListener { getOnTryAction() }
            textErrorTitle.setText(error.title)
            textErrorDescription.setText(error.description)
        }
    }

    private fun SearchViewState.Shimmer.shimmer() {
        binding.shimmer.startShimmer()
    }

    private fun layoutHandle(state: SearchViewState) {
        with(binding) {
            shimmer.stopShimmer()
            shimmer.isVisible = state is SearchViewState.Shimmer
            llMain.isVisible = state is SearchViewState.Content
            errorLayout.isVisible = state is SearchViewState.Error
        }
    }

    private fun getOnTryAction() {
        return myViewModel.loadSearchItemsList(args.id, "")
    }
}