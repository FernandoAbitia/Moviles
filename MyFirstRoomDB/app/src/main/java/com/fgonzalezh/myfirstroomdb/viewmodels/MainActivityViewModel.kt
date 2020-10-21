package com.fgonzalezh.myfirstroomdb.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.fgonzalezh.myfirstroomdb.models.entities.Content
import com.fgonzalezh.myfirstroomdb.repositories.ContentRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val contactRepository = ContentRepository(application)

    fun getContents() : LiveData<List<Content>> = liveData {
        emitSource(contactRepository.getAllContentLiveData())
    }
}