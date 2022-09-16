package com.ilein.cosmodroid

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

internal class MenuSearchAdapter: ListAdapter<SearchItem, MenuSearchAdapter.ViewHolder>(SEARCH_ITEM_COMPARATOR) {

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var image: ImageView = itemView.findViewById(R.id.ivPic)
        private var itemTitle: TextView = itemView.findViewById(R.id.tvMenuItem)

        fun bind(searchItem: SearchItem) {
            /*
            itemView.setOnClickListener {
                clickListener?.onItemClick(searchItem)
            }
             */
            image.requestLayout()
            itemTitle.text = searchItem.textInfo

            image.load(searchItem.image) {
                transformations(RoundedCornersTransformation(16f))
            }
        }
    }

    companion object {
        val SEARCH_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<SearchItem>() {
            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun getChangePayload(oldItem: SearchItem, newItem: SearchItem): Any? {
                return null
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuSearchAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_menu_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuSearchAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}