package com.ilein.cosmodroid.search.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.scaleMatrix
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.domain.model.SearchItemModel

internal class SearchAdapter: ListAdapter<SearchResultModel, SearchAdapter.ViewHolder>(
    SEARCH_ITEM_COMPARATOR
) {
    private var clickListener: IOnItemClick? = null

    private val items: MutableList<SearchItemModel> = mutableListOf()

    fun setClickListener(listener: IOnItemClick?) {
        clickListener = listener
    }

    fun setItems(newItems: List<SearchItemModel>) {
        DiffUtil.calculateDiff(DiffCallback(items, newItems.toList())).also { result ->
            items.clear()
            items.addAll(newItems.toList())
            result.dispatchUpdatesTo(this)
        }
    }

    interface IOnItemClick {
        fun onItemClick(searchItem: SearchItemModel)
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var image: ImageView = itemView.findViewById(R.id.ivImage)
        private var itemTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private var description: TextView = itemView.findViewById(R.id.tvDescription)

        fun bind(searchItem: SearchItemModel) {

            itemView.setOnClickListener {
                clickListener?.onItemClick(searchItem)
            }

            image.requestLayout()
            itemTitle.text = searchItem.title
            description.text = searchItem.description

            image.load(searchItem.imgUrl) {
                transformations(RoundedCornersTransformation(16f))
                scale(Scale.FILL)
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
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}