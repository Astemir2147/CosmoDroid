package com.ilein.cosmodroid.feature_news_list.presentation.news_detail

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import coil.load
import com.ilein.cosmodroid.ViewBindingFragment
import com.ilein.cosmodroid.databinding.FragmentDetailNewsBinding
import com.ilein.cosmodroid.common.modalBottomSheet.presentation.ModalBottomSheet
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem

class DetailNewsFragment : ViewBindingFragment<FragmentDetailNewsBinding>() {
    private lateinit var player: ExoPlayer
    private lateinit var newsItem: NewsItem

    override val initBinding: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentDetailNewsBinding =
        FragmentDetailNewsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withSafeBinding {
            topAppBar.setNavigationOnClickListener { findNavController().popBackStack() }
        }
        newsItem = requireArguments().getSerializable(NEWS_ITEM) as NewsItem
        itemHandler(newsItem)
        initializePlayer()
    }

    private fun initializePlayer() {
        setupPlayer()
        extractYoutubeVideo()

        nonNullBinding.newsVideo.player = player
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        with(player) {
            playWhenReady = true
            seekTo(0, 0)
            prepare()
            addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    nonNullBinding.newsVideo.isVisible = false
                }
            })
        }
    }

    private fun extractYoutubeVideo() {
        class PlayerExtractor : YouTubeExtractor(requireContext()) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {
                    val mediaItem = MediaItem.fromUri(ytFiles[YT_FILES_ITAG].url)
                    player.setMediaItem(mediaItem)

                }
            }
        }
        PlayerExtractor().extract(newsItem.videoUrl)
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
        withSafeBinding {
            nameOfNews.text = newsItem.name
            dateOfNewsDetail.text = newsItem.name
            typeOfNewsDetail.text = newsItem.type
            newsContent.text = newsItem.description
            imageOfDetailNews.load(newsItem.featureImage)
        }
    }

    companion object {
        private const val NEWS_ITEM = "newsItem"
        private const val YT_FILES_ITAG = 22
    }
}