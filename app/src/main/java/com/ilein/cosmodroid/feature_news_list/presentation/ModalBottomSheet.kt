package com.ilein.cosmodroid.feature_news_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.core.database.dao.NewsDao
import com.ilein.cosmodroid.databinding.FragmentModalBottomSheetBinding
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.model.toNewsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.get

class ModalBottomSheet: BottomSheetDialogFragment() {
    private var binding: FragmentModalBottomSheetBinding? = null
    private var id: Int? = 0
    private lateinit var date: String
    private lateinit var description: String
    private lateinit var image: String
    private lateinit var type: String
    private lateinit var name: String
    private lateinit var database: NewsDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_modal_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = get<NewsDao>()
        binding = FragmentModalBottomSheetBinding.bind(view)
        binding!!.addToFavourite.setOnClickListener { addToFavourite() }
    }
    private fun addToFavourite() {
        lifecycleScope.launchWhenResumed {
            withContext(Dispatchers.IO) {
                database.setFavouritesNews(getDate().toNewsEntity())
            }
        }
    }

    private fun getDate(): NewsItem {
        arguments?.let {
            id = arguments?.getInt(ARG_PARAM_ID)
            date = arguments?.getString(ARG_PARAM_DATE).toString()
            description = arguments?.getString(ARG_PARAM_DESCRIPTION).toString()
            image = arguments?.getString(ARG_PARAM_IMAGE).toString()
            type = arguments?.getString(ARG_PARAM_TYPE).toString()
            name = arguments?.getString(ARG_PARAM_NAME).toString()
        }
        return NewsItem(id!!, date, description, image, type, name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        const val TAG = "ModalBottomSheet"
        private const val ARG_PARAM_ID = "paramIdOfNews"
        private const val ARG_PARAM_DATE = "paramDateOfNews"
        private const val ARG_PARAM_TYPE = "paramTypeOfNews"
        private const val ARG_PARAM_DESCRIPTION = "paramPreviewOfNews"
        private const val ARG_PARAM_IMAGE = "paramImageOfNews"
        private const val ARG_PARAM_NAME = "paramNameOfNews"

        @JvmStatic
        fun newInstance(newsItem: NewsItem) =
            ModalBottomSheet().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM_ID, newsItem.id)
                    putString(ARG_PARAM_DATE, newsItem.date)
                    putString(ARG_PARAM_TYPE, newsItem.type)
                    putString(ARG_PARAM_DESCRIPTION, newsItem.description)
                    putString(ARG_PARAM_IMAGE, newsItem.featureImage)
                    putString(ARG_PARAM_NAME, newsItem.name)
                }
            }
    }
}