package com.ilein.cosmodroid.feature_favourites_news.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ilein.cosmodroid.core.database.dao.NewsDao
import com.ilein.cosmodroid.core.database.entity.NewsEntity

/**
 * My database
 *
 * @author Zashaev Astemir on 2022-10-23
 */
@Database(
    entities = [NewsEntity::class], version = 1, exportSchema = false
)

abstract class MyDatabase: RoomDatabase() {
    /** для получения зависимости в data module в koin */
    abstract fun getDao(): NewsDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, MyDatabase::class.java, "MyDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }
}