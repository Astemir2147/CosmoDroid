package com.ilein.cosmodroid.search.presentation

import androidx.recyclerview.widget.DiffUtil
import com.ilein.cosmodroid.search.domain.model.SearchItemModel

class DiffCallback(private val oldList: List<SearchItemModel>, private val newList: List<SearchItemModel>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}