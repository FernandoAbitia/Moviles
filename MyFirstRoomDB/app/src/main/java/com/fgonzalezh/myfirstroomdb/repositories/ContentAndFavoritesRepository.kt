package com.fgonzalezh.myfirstroomdb.repositories

import android.content.Context
import androidx.room.withTransaction
import com.fgonzalezh.myfirstroomdb.models.dao.FavoriteDAO
import com.fgonzalezh.myfirstroomdb.models.entities.Content
import com.fgonzalezh.myfirstroomdb.models.entities.Favorite
import com.fgonzalezh.myfirstroomdb.models.roomdb.ContentDB

class ContentAndFavoritesRepository(context: Context) {
    val contentDB = ContentDB.getInstance(context)
    val contentDAO = contentDB.contentDAO()
    val favoriteDAO = contentDB.favoriteDAO()

    suspend fun insertContentAndFavorites(content: Content, favorites: List<Content>){
        contentDB.withTransaction {
            val id = contentDAO.insertContent(content)
            val favoriteList = favorites.map{
                Favorite(id!!, it.id!!)
            }

            favoriteDAO.insertFavorites(favoriteList)
        }
    }
    suspend fun getFavorites(contentId: Long): List <Content>{
        val favorites = favoriteDAO.getFavorites(contentId)
        val ids = favorites.map { it.contentFavoriteId }
        val contents = contentDAO.getAllContentByIds(ids)

        return contents
    }

}