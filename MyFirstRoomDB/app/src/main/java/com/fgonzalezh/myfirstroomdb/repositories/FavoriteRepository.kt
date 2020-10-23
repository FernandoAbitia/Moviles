package com.fgonzalezh.myfirstroomdb.repositories

import android.content.Context
import com.fgonzalezh.myfirstroomdb.models.entities.Favorite
import com.fgonzalezh.myfirstroomdb.models.roomdb.ContentDB

class FavoriteRepository(context: Context) {

    private val contentDB = ContentDB.getInstance(context)
    private val favoriteDAO = contentDB.favoriteDAO()

    suspend fun insertFavorite(favorite: Favorite){
        favoriteDAO.insert(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite){
        favoriteDAO.deleteFavorite(favorite)
    }

}