package com.fgonzalezh.myfirstroomdb.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.fgonzalezh.myfirstroomdb.models.entities.Content
import com.fgonzalezh.myfirstroomdb.repositories.ContentAndFavoritesRepository
import com.fgonzalezh.myfirstroomdb.repositories.ContentRepository

class ActivityAddContactViewModel(application: Application) : AndroidViewModel(application) {
    private val contentRepository = ContentRepository(application)
    private val contentAndFavoritesRepository = ContentAndFavoritesRepository(application)
    private val TAG = ActivityAddContactViewModel::class.java.name

    fun insertContent(content: Content, favorites: List<Content>) = liveData {
        try {
            contentAndFavoritesRepository.insertContentAndFavorites(content, favorites)
            emit(true)
        } catch (ex: Exception) {
            Log.e(TAG, ex.message, ex)
            emit(false)
        }
    }

    fun getAllContent() : LiveData<List<Content>> = liveData {
        emit(contentRepository.getAllContents())
    }
}