package com.fgonzalezh.myfirstroomdb.repositories

import android.content.Context
import com.fgonzalezh.myfirstroomdb.models.entities.Favorite
import com.fgonzalezh.myfirstroomdb.models.roomdb.ContentDB

class FavoriteRepository(context: Context) {

    private val contentDB = ContentDB.getInstance(context)
    private val contentDAO = contentDB.favoriteDAO()

    suspend fun insertFavorite(favorite: Favorite){
        contentDAO.insert(favorite)
    }

}