package com.ilein.cosmodroid.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.ilein.cosmodroid.databinding.ItemSearchDetailLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchItemDetailFragment : Fragment() {

    private val args: SearchItemDetailFragmentArgs by navArgs()

    private var _binding: ItemSearchDetailLayoutBinding? = null
    private val binding get() = _binding!!

    private val myViewModel by viewModel<SearchItemDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemSearchDetailLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadItems()
    }

    private fun loadItems() {
        myViewModel.loadSearchItem(args.typeId, args.id, args.idStr)

        myViewModel.searchAgencyLiveData.observe(requireActivity()) {
            binding.tvDetailTitle.text = it.title
            binding.ivDetailImage.isVisible = !it.imgUrl.isNullOrBlank()
            if (!it.imgUrl.isNullOrBlank()) {
                binding.ivDetailImage.load(it.imgUrl) {
                    transformations(RoundedCornersTransformation(16f))
                    scale(Scale.FILL)
                }
            } else {
                binding.ivDetailImage.visibility = View.GONE
            }
            binding.tvDetailDescription.text = it.description
            binding.tvDetailFullDescription.text = it.fullDescription
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}