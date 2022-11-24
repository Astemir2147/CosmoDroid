package com.ilein.cosmodroid.search.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.ilein.cosmodroid.R
import com.ilein.cosmodroid.search.data.model.EnumSearchItems
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.domain.model.SearchItemModel

internal class SearchAdapter(private val onItemClick: (Int, Int, String) -> Unit) :
    ListAdapter<SearchResultModel, SearchAdapter.ViewHolder>(
        SEARCH_ITEM_COMPARATOR
    ) {

    private val items: MutableList<SearchItemModel> = mutableListOf()

    fun setItems(newItems: List<SearchItemModel>) {
        DiffUtil.calculateDiff(DiffCallback(items, newItems.toList())).also { result ->
            items.clear()
            items.addAll(newItems.toList())
            result.dispatchUpdatesTo(this)
        }
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var image: ImageView = itemView.findViewById(R.id.ivImage)
        private var imageCosmo: ImageView = itemView.findViewById(R.id.ivImageCosmo)
        private var itemTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private var description: TextView = itemView.findViewById(R.id.tvDescription)

        fun bind(onItemClick: (Int, Int, String) -> Unit, searchItem: SearchItemModel) {

            itemView.setOnClickListener {
                onItemClick(searchItem.type.id, searchItem.id ?: 0, searchItem.idStr)
            }

            image.requestLayout()
            imageCosmo.requestLayout()
            itemTitle.text = searchItem.title

            if (searchItem.imgUrl.isNullOrBlank() || searchItem.type == EnumSearchItems.ASTRONAUTS) {
                image.visibility = View.GONE
                description.maxLines = 7
            } else {
                image.visibility = View.VISIBLE
                description.maxLines = 3
            }
            description.text = searchItem.description

            if (searchItem.type == EnumSearchItems.ASTRONAUTS) {
                imageCosmo.scaleType = ImageView.ScaleType.FIT_CENTER
                if (searchItem.imgUrl.isNullOrBlank()) {
                    imageCosmo.visibility = View.GONE
                } else {
                    imageCosmo.visibility = View.VISIBLE
                }
                if (imageCosmo.isVisible) {
                    imageCosmo.load(searchItem.imgUrl) {
                        transformations(RoundedCornersTransformation(16f))
                        scale(Scale.FILL)
                    }
                }
            } else {
                image.scaleType = ImageView.ScaleType.CENTER_CROP
                imageCosmo.visibility = View.GONE
                if (image.isVisible) {
                    image.load(searchItem.imgUrl) {
                        transformations(RoundedCornersTransformation(16f))
                        scale(Scale.FIT)
                    }
                }
            }
        }
    }


    companion object {
        val SEARCH_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<SearchResultModel>() {
            override fun areContentsTheSame(
                oldItem: SearchResultModel,
                newItem: SearchResultModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: SearchResultModel,
                newItem: SearchResultModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun getChangePayload(
                oldItem: SearchResultModel,
                newItem: SearchResultModel
            ): Any? {
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
        holder.bind(onItemClick, items[position])
    }

    override fun getItemCount(): Int = items.size
}