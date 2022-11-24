package com.ilein.cosmodroid.feature_favourites_news.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ilein.cosmodroid.feature_favourites_news.presentation.model.DbNewsItem

class NewsDiffUtilCallback(private val oldList: List<DbNewsItem>, private val newList: List<DbNewsItem>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

}