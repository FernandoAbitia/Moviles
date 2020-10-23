package com.fgonzalezh.myfirstroomdb.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.fgonzalezh.myfirstroomdb.models.entities.Content
import com.fgonzalezh.myfirstroomdb.repositories.ContentAndFavoritesRepository
import com.fgonzalezh.myfirstroomdb.repositories.ContentRepository
import kotlinx.coroutines.launch

class ActivityAddContactViewModel(application: Application) : AndroidViewModel(application) {
    private val contentRepository = ContentRepository(application)
    private val contentAndFavoritesRepository = ContentAndFavoritesRepository(application)
    private val TAG = ActivityAddContactViewModel::class.java.name
    private val insertLiveData = MutableLiveData<Boolean>()

    fun insertContent(content: Content, favorites: List<Content>) = viewModelScope.launch{
        try {
            contentAndFavoritesRepository.insertContentAndFavorites(content, favorites)
            insertLiveData.postValue(true)
        }catch(ex: Exception){
            Log.e(TAG,ex.message,ex)
            insertLiveData.postValue(false)
        }
    }
    fun notifyInsertContent() : LiveData<Boolean> = insertLiveData

    fun getAllContent() : LiveData<List<Content>> = liveData {
        emit(contentRepository.getAllContents())
    }
}