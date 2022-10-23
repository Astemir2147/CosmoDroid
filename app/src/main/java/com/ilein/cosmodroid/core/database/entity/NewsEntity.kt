package com.ilein.cosmodroid.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilein.cosmodroid.core.database.DatabaseConst.DATE_OF_NEWS
import com.ilein.cosmodroid.core.database.DatabaseConst.FAVOURITES_NEWS
import com.ilein.cosmodroid.core.database.DatabaseConst.ID
import com.ilein.cosmodroid.core.database.DatabaseConst.NEWS_CONTENT
import com.ilein.cosmodroid.core.database.DatabaseConst.NEWS_NAME
import com.ilein.cosmodroid.core.database.DatabaseConst.TYPE_OF_NEWS

@Entity(tableName = FAVOURITES_NEWS)
data class NewsEntity(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val contractId: Long,

    @ColumnInfo(name = NEWS_CONTENT)
    val newsContent: String,

    @ColumnInfo(name = DATE_OF_NEWS)
    val dateOfNewsName: String,

    @ColumnInfo(name = TYPE_OF_NEWS)
    val typeOfNewsName: String,

    @ColumnInfo(name = NEWS_NAME)
    val nameOfNewsName: String,
)
