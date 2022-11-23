package com.ilein.cosmodroid.feature_news_list.presentation.news_detail

import android.os.Bundle
import android.util.SparseArray
import android.view.View
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
import com.ilein.cosmodroid.common.modalBottomSheet.presentation.ModalBottomSheet
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.state.DetailNewsViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailNewsFragment: Fragment(R.layout.fragment_detail_news) {
    private lateinit var binding: FragmentDetailNewsBinding
    private val newsViewModel by viewModel<DetailNewsViewModel>()
    private lateinit var player: ExoPlayer
    private lateinit var newsItem: NewsItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailNewsBinding.bind(view)
        binding.goBackStack.setOnClickListener { findNavController().popBackStack() }
        binding.detailSpinMenu.setOnClickListener { showBottomSheet(newsItem) }
        newsItem = requireArguments().getSerializable(NEWS_ITEM) as NewsItem
        itemHandler(newsItem)
        initializePlayer()
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        val videoLink = newsItem.videoUrl
        val playWhenReady = true
        val currentItem = 0
        val playbackPosition: Long = 0
        player.playWhenReady = playWhenReady
        player.seekTo(currentItem, playbackPosition)
        player.prepare()

        class PlayerExtractor : YouTubeExtractor(requireContext()) {
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
        PlayerExtractor().extract(videoLink)
        binding.newsVideo.player = player
    }

    private fun showBottomSheet(newsItem: NewsItem) {
        val modalBottomSheet = ModalBottomSheet.newInstance(newsItem)
        modalBottomSheet.show(parentFragmentManager, ModalBottomSheet.TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.release()
    }

    private fun itemHandler(newsItem: NewsItem) {
        with(binding) {
            nameOfNews.text = newsItem.name
            dateOfNewsDetail.text = newsItem.name
            typeOfNewsDetail.text = newsItem.type
            newsContent.text = newsItem.description
            imageOfDetailNews.load(newsItem.featureImage)
        }
    }

    private fun DetailNewsViewState.Error.handle() {
        with(binding) {
            btnErrorTryAgain.setOnClickListener {
                getOnTryAction(newsItem.id)
            }
            textErrorTitle.setText(error.title)
            textErrorDescription.setText(error.description)
        }
    }

    private fun getOnTryAction(id: Int) {
        return newsViewModel.getNews(id)
    }

    companion object {
        private const val NEWS_ITEM = "newsItem"
    }
}