package com.ilein.cosmodroid.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilein.cosmodroid.databinding.SearchFragmentBinding
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
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.title
        binding.tvSearchName.text = args.title
        myViewModel.loadSearchItemsList(args.id)

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
        myViewModel.searchListLiveData.observe(requireActivity()) {
            binding.rvSearchResults.apply {
                adapter = searchAdapter
                searchAdapter.setItems(it)
            }
        }
    }
}