package com.ilein.cosmodroid.search.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.databinding.ItemSearchDetailLayoutBinding
import com.ilein.cosmodroid.search.domain.model.SearchItemDetailModel
import com.ilein.cosmodroid.search.state.SearchItemViewState
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchItemDetailFragment : Fragment() {

    private val args: SearchItemDetailFragmentArgs by navArgs()

    private var _binding: ItemSearchDetailLayoutBinding? = null
    private val binding get() = _binding!!

    private val myViewModel by viewModel<SearchItemDetailViewModel>()
    private lateinit var searchItemDetail: SearchItemDetailModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemSearchDetailLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goBackStack.setOnClickListener { findNavController().popBackStack() }
        binding.searchDetailShare.setOnClickListener { shareItem(searchItemDetail) }

        loadItems()
    }

    private fun loadItems() {
        myViewModel.searchItemLiveData.observe(requireActivity(), ::handleSearchState)
        myViewModel.loadSearchItem(args.typeId, args.id, args.idStr)
    }

    private fun handleSearchState(searchState: SearchItemViewState) {
        layoutHandle(searchState)
        when (searchState) {
            is SearchItemViewState.Content -> searchState.content()
            is SearchItemViewState.Error -> searchState.handle()
            else -> {}
        }
    }

    private fun layoutHandle(state: SearchItemViewState) {
        with(binding) {
            llMain.isVisible = state is SearchItemViewState.Content
            errorLayout.isVisible = state is SearchItemViewState.Error
        }
    }

    private fun SearchItemViewState.Content.content() {
        searchItemDetail = searchItem
        binding.tvDetailTitle.text = searchItem.title
        binding.ivDetailImage.isVisible = !searchItem.imgUrl.isNullOrBlank()
        if (!searchItem.imgUrl.isNullOrBlank()) {
            binding.ivDetailImage.load(searchItem.imgUrl) {
                transformations(RoundedCornersTransformation(16f))
                scale(Scale.FILL)
            }
        } else {
            binding.ivDetailImage.visibility = View.GONE
        }
        binding.tvDetailDescription.text = searchItem.description
        binding.tvDetailFullDescription.text = searchItem.fullDescription
    }

    private fun SearchItemViewState.Error.handle() {
        with(binding) {
            btnErrorTryAgain.setOnClickListener {
                getOnTryAction(args.typeId, args.id, args.idStr)
            }
            textErrorTitle.setText(error.title)
            textErrorDescription.setText(error.description)
        }
    }

    private fun getOnTryAction(typeId: Int, id: Int, idStr: String) {
        return myViewModel.loadSearchItem(typeId, id, idStr)
    }

    private fun shareItem(searchItem: SearchItemDetailModel) {
        val typeShare = "text/plain"
        val extraText: String = getString(R.string.share_search_item_text).format(
            searchItem.title, searchItem.imgUrl,
            searchItem.description, searchItem.fullDescription
        )

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, extraText)
            type = typeShare
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}