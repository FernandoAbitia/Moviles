package com.fgonzalezh.myfirstroomdb.models.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fgonzalezh.myfirstroomdb.models.entities.Favorite

@Dao
abstract class FavoriteDAO {

    @Insert
    abstract suspend fun insert(favorite: Favorite)

    @Insert
    abstract suspend fun insertFavorites(favorites: List<Favorite>)

    @Query("SELECT * FROM Favorite WHERE contentFirstId=:contentId")
    abstract suspend fun getFavorites(contentId: Long): List<Favorite>

    @Delete
    abstract suspend fun deleteFavorite(favorite: Favorite)
}