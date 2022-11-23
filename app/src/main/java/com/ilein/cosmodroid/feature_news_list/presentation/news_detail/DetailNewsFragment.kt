package com.ilein.cosmodroid.feature_news_list.presentation.news_detail

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.media3.common.MediaItem
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
        kotlin.runCatching {
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
            nonNullBinding.newsVideo.player = player
        }.onFailure {
            Toast.makeText(requireContext(), "Rer", Toast.LENGTH_SHORT).show()
        }
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
    }
}