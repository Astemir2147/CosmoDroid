package com.ilein.cosmodroid.feature_news_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.databinding.FragmentModalBottomSheetBinding
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem

class ModalBottomSheet: BottomSheetDialogFragment() {
    private var binding: FragmentModalBottomSheetBinding? = null
    private var param1: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_modal_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM_DATE)
        }
        binding = FragmentModalBottomSheetBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        const val TAG = "ModalBottomSheet"
        private const val ARG_PARAM_DATE = "paramDateOfNews"
        private const val ARG_PARAM_TYPE = "paramTypeOfNews"
        private const val ARG_PARAM_DESCRIPTION = "paramPreviewOfNews"
        private const val ARG_PARAM_IMAGE = "paramImageOfNews"
        @JvmStatic
        fun newInstance(newsItem: NewsItem) =
            ModalBottomSheet().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_DATE, newsItem.date)
                    putString(ARG_PARAM_TYPE, newsItem.type)
                    putString(ARG_PARAM_DESCRIPTION, newsItem.description)
                    putString(ARG_PARAM_IMAGE, newsItem.featureImage)
                }
            }
    }
}