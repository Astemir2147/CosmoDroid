package com.ilein.cosmodroid.search.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.search.data.model.SearchResultModel

internal class SearchAdapter: ListAdapter<SearchResultModel, SearchAdapter.ViewHolder>(
    SEARCH_ITEM_COMPARATOR
) {
    private var clickListener: IOnItemClick? = null

    fun setClickListener(listener: IOnItemClick?) {
        clickListener = listener
    }

    interface IOnItemClick {
        fun onItemClick(searchItem: SearchResultModel)
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var image: ImageView = itemView.findViewById(R.id.ivPic)
        private var itemTitle: TextView = itemView.findViewById(R.id.tvMenuItem)

        fun bind(searchItem: SearchResultModel) {

            itemView.setOnClickListener {
                clickListener?.onItemClick(searchItem)
            }

            image.requestLayout()
            itemTitle.text = searchItem.name

            image.load(searchItem.image_url) {
                transformations(RoundedCornersTransformation(16f))
            }
        }
    }

    companion object {
        val SEARCH_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<SearchResultModel>() {
            override fun areContentsTheSame(oldItem: SearchResultModel, newItem: SearchResultModel): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: SearchResultModel, newItem: SearchResultModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun getChangePayload(oldItem: SearchResultModel, newItem: SearchResultModel): Any? {
                return null
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}