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
import com.ilein.cosmodroid.search.data.model.EnumSearchItems

internal class MenuSearchAdapter: ListAdapter<EnumSearchItems, MenuSearchAdapter.ViewHolder>(
    SEARCH_ITEM_COMPARATOR
) {

    private var clickListener: IOnItemClick? = null

    fun setClickListener(listener: IOnItemClick?) {
        clickListener = listener
    }

    interface IOnItemClick {
        fun onItemClick(searchItem: EnumSearchItems)
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var image: ImageView = itemView.findViewById(R.id.ivPic)
        private var itemTitle: TextView = itemView.findViewById(R.id.tvMenuItem)

        fun bind(searchItem: EnumSearchItems) {

            itemView.setOnClickListener {
                clickListener?.onItemClick(searchItem)
            }

            image.requestLayout()
            itemTitle.text = searchItem.title

            image.load(searchItem.imgUrl) {
                transformations(RoundedCornersTransformation(16f))
            }
        }
    }

    companion object {
        val SEARCH_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<EnumSearchItems>() {
            override fun areContentsTheSame(oldItem: EnumSearchItems, newItem: EnumSearchItems): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: EnumSearchItems, newItem: EnumSearchItems): Boolean {
                return oldItem.id == newItem.id
            }

            override fun getChangePayload(oldItem: EnumSearchItems, newItem: EnumSearchItems): Any? {
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
            .inflate(R.layout.item_menu_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}