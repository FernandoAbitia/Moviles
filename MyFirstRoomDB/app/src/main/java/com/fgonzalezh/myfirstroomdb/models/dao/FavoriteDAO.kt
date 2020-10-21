package com.fgonzalezh.myfirstroomdb.models.dao

import androidx.room.Dao
import androidx.room.Insert
import com.fgonzalezh.myfirstroomdb.models.entities.Favorite

@Dao
abstract class FavoriteDAO {

    @Insert
    abstract suspend fun insert(favorite: Favorite)

    @Insert
    abstract suspend fun insertFavorites(favorites: List<Favorite>)
}