package com.fgonzalezh.myfirstroomdb.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.fgonzalezh.myfirstroomdb.models.entities.Content
import com.fgonzalezh.myfirstroomdb.models.entities.Favorite
import com.fgonzalezh.myfirstroomdb.repositories.ContentAndFavoritesRepository
import com.fgonzalezh.myfirstroomdb.repositories.FavoriteRepository
import kotlinx.coroutines.launch

class BottomSheetViewModel(application: Application) : AndroidViewModel(application) {
    private val contentAndFavoritesRepository = ContentAndFavoritesRepository(application)
    private val favoritesRepository = FavoriteRepository(application)
    private val liveDataFavorites = MutableLiveData<List<Content>>()

    fun getFavorites(contentId: Long): LiveData<List<Content>> = liveData {
        emitSource(liveDataFavorites)

        val favorites = contentAndFavoritesRepository.getFavorites(contentId)

        liveDataFavorites.postValue(favorites)
    }

    suspend fun getFavoritesRepository(contentId: Long){
        val favorites = contentAndFavoritesRepository.getFavorites(contentId)
        liveDataFavorites.postValue(favorites)
    }

    fun deleteFavorite(contentId: Long,favoriteId: Long)= viewModelScope.launch{
        favoritesRepository.deleteFavorite(
            Favorite(contentId,favoriteId)
        )
        getFavoritesRepository(contentId)
    }
}