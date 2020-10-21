package com.fgonzalezh.myfirstroomdb.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.fgonzalezh.myfirstroomdb.models.dao.ContentDAO
import com.fgonzalezh.myfirstroomdb.models.entities.Content
import com.fgonzalezh.myfirstroomdb.models.roomdb.ContentDB

class ContentRepository(context: Context) {
    private val contactDB = ContentDB.getInstance(context)
    private val contactDAO = contactDB.contentDAO()

    suspend fun insertContent(content: Content) {
        contactDAO.insertContent(content)
    }

    suspend fun getAllContents(): List<Content> {
        return contactDAO.getAllContentSync()
    }

    fun getAllContentLiveData(): LiveData<List<Content>>{
        return contactDAO.getAllContent()
    }
}