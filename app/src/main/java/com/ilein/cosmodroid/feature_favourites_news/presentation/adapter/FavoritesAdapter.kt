package com.ilein.cosmodroid.feature_favourites_news.presentation.adapter

import com.ilein.cosmodroid.R
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.ilein.cosmodroid.databinding.ItemNewsLayoutBinding
import com.ilein.cosmodroid.feature_favourites_news.presentation.model.DbNewsItem

class FavoritesAdapter(
    private val showBottomSheet: (newsIntent: DbNewsItem) -> Unit,
    private val showDetailNews: (newsIntent: DbNewsItem) -> Unit
) :
    RecyclerView.Adapter<FavoritesAdapter.NewsViewHolder>() {
    class NewsViewHolder(val itemBinding: ItemNewsLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private var newsList: List<DbNewsItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            ItemNewsLayoutBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_news_layout, parent, false)
            )
        )

    fun setNewsList(list: List<DbNewsItem>) {
        newsList = list
    }

    fun getNewsList() = newsList

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder.itemBinding) {
            spinMenu.setOnClickListener { showBottomSheet.invoke(newsList[position]) }
            dateOfNews.text = newsList[position].date
            typeOfNews.text = newsList[position].type
            previewOfNews.text = newsList[position].description
            imageOfNews.load(newsList[position].featureImage)
            root.setOnClickListener { showDetailNews.invoke(newsList[position]) }
        }
    }

    override fun getItemCount() = newsList.size
}