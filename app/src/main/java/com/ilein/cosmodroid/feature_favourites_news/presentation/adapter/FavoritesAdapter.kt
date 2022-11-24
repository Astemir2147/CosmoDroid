package com.ilein.cosmodroid.feature_favourites_news.presentation.adapter

import com.ilein.cosmodroid.R
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.ilein.cosmodroid.databinding.ItemNewsLayoutBinding
import com.ilein.cosmodroid.feature_favourites_news.presentation.model.DbNewsItem

interface ItemCallback {
    fun showBottomSheet(position: Int)
    fun showDetailNews(position: Int)
}

class FavoritesAdapter(
    private val showBottomSheet: (newsIntent: DbNewsItem) -> Unit,
    private val showDetailNews: (newsIntent: DbNewsItem) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.NewsViewHolder>(), ItemCallback {

    private val newsList: ArrayList<DbNewsItem> = arrayListOf()

    class NewsViewHolder(
        private val itemBinding: ItemNewsLayoutBinding,
        itemCallback: ItemCallback
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        init {
            with(itemBinding) {
                spinMenu.setOnClickListener { itemCallback.showBottomSheet(adapterPosition) }
                root.setOnClickListener { itemCallback.showDetailNews(adapterPosition) }
            }
        }

        fun bind(item: DbNewsItem) {
            with(itemBinding) {
                dateOfNews.text = item.date
                typeOfNews.text = item.type
                previewOfNews.text = item.description
                imageOfNews.load(item.featureImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            ItemNewsLayoutBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_news_layout, parent, false),
            ),
            this
        )

    fun setNewsList(list: List<DbNewsItem>) {
        newsList.clear()
        newsList.addAll(list)
    }

    fun getNewsList() = newsList

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount() = newsList.size

    override fun showBottomSheet(position: Int) {
        showBottomSheet.invoke(newsList[position])
    }

    override fun showDetailNews(position: Int) {
        showDetailNews.invoke(newsList[position])
    }
}