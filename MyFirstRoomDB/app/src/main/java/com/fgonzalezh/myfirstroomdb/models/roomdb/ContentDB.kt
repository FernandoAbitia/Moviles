package com.fgonzalezh.myfirstroomdb.models.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fgonzalezh.myfirstroomdb.models.dao.ContentDAO
import com.fgonzalezh.myfirstroomdb.models.dao.FavoriteDAO
import com.fgonzalezh.myfirstroomdb.models.entities.Content
import com.fgonzalezh.myfirstroomdb.models.entities.Favorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Content::class, Favorite:: class],
    version = 2,
    exportSchema = true
)
abstract class ContentDB : RoomDatabase() {
    abstract fun contentDAO(): ContentDAO
    abstract fun favoriteDAO(): FavoriteDAO

    companion object {
        @JvmStatic
        @Volatile
        private var instance: ContentDB? = null

        @Synchronized
        fun getInstance(context: Context): ContentDB {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    ContentDB::class.java,
                    "content.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                CoroutineScope(Dispatchers.IO).launch {
                    instance?.initDB()
                }
            }

            return instance as ContentDB
        }
    }

    suspend fun initDB() {
        val contentDAO = contentDAO()
        val favoriteDao = favoriteDAO()
        if (contentDAO.getAllContentSync().isEmpty()) {
            contentDAO.insertContent(
                Content(
                    name = "Francisco",
                    lastName = "Gonzalez",
                    age = 32
                )
            )

            contentDAO.insertContent(
                Content(
                    name = "Pedro",
                    lastName = "Sanchez",
                    age = 45
                )
            )
            favoriteDao.insert(
                Favorite(
                    1,
                    2
                )
            )
        }
    }
}

