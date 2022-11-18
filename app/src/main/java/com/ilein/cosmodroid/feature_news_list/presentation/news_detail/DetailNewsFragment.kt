package com.ilein.cosmodroid.feature_news_list.presentation.news_detail

import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import coil.load
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.databinding.FragmentDetailNewsBinding
import com.ilein.cosmodroid.feature_news_list.presentation.state.DetailNewsViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailNewsFragment : Fragment(R.layout.fragment_detail_news) {
    private lateinit var binding: FragmentDetailNewsBinding
    private val newsViewModel by viewModel<DetailNewsViewModel>()
    private lateinit var player: ExoPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailNewsBinding.bind(view)
        binding.topAppBar.setNavigationOnClickListener { findNavController().popBackStack() }
        setDate()
        initializePlayer()
    }


    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        val videoLink = requireArguments().getString(ARG_PARAM_ViDEO_URL)
        val playWhenReady = true
        val currentItem = 0
        val playbackPosition: Long = 0
        player.playWhenReady = playWhenReady
        player.seekTo(currentItem, playbackPosition)
        player.prepare()

        class YouTubeExtractor1 : YouTubeExtractor(requireContext()) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {
                    val iTag = 22
                    val downloadUrl = ytFiles[iTag].url
                    val mediaItem = MediaItem.fromUri(downloadUrl)
                    player.setMediaItem(mediaItem)
                }
            }
        }
        YouTubeExtractor1().extract(videoLink)
        binding.newsVideo.player = player
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.release()
    }

    private fun newsViewModel(state: Boolean): Boolean {
        when (state) {
            true -> binding.detailNewsLayout.isVisible = true
            false -> binding.detailErrorLayout.isVisible = true
        }
        return state
    }

    private fun setDate() {
        with(binding) {
            val id = requireArguments().getInt(ARG_PARAM_ID)
            if (newsViewModel(id!=null)) {
                nameOfNews.text = requireArguments().getString(ARG_PARAM_NAME)
                dateOfNewsDetail.text = requireArguments().getString(ARG_PARAM_DATE)
                typeOfNewsDetail.text = requireArguments().getString(ARG_PARAM_TYPE)
                newsContent.text = requireArguments().getString(ARG_PARAM_DESCRIPTION)
                imageOfDetailNews.load(requireArguments().getString(ARG_PARAM_IMAGE))
            }
        }
    }

    private fun DetailNewsViewState.Error.handle() {
        with(binding) {
            btnErrorTryAgain.setOnClickListener {
                getOnTryAction(requireArguments().getInt(ARG_PARAM_ID))
            }
            textErrorTitle.setText(error.title)
            textErrorDescription.setText(error.description)
        }
    }

    private fun getOnTryAction(id: Int) {
        return newsViewModel.getNews(id)
    }

    companion object {
        private const val ARG_PARAM_ID = "paramIdOfNews"
        private const val ARG_PARAM_ViDEO_URL = "paramUrlVideoOfNews"
        private const val ARG_PARAM_DATE = "paramDateOfNews"
        private const val ARG_PARAM_TYPE = "paramTypeOfNews"
        private const val ARG_PARAM_DESCRIPTION = "paramPreviewOfNews"
        private const val ARG_PARAM_IMAGE = "paramImageOfNews"
        private const val ARG_PARAM_NAME = "paramNameOfNews"
    }
}