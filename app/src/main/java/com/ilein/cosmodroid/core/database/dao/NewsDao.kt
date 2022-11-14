package com.ilein.cosmodroid.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilein.cosmodroid.core.database.entity.NewsEntity

/**
 * Database Access Object для получения изюранных новостей
 *
 * @author Astemir Zashaev on 2022-10-23
 */
@Dao
abstract class NewsDao {
    /** Возвращает все новости */
    @Query("SELECT * FROM favourites_news")
    abstract fun getAllFavouritesNews(): List<NewsEntity>

    /** Добавляет пост с новостью в базу данных */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun setFavouritesNews(contract: NewsEntity)
}