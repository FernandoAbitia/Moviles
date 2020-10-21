package com.fgonzalezh.myfirstroomdb.models.entities

import androidx.room.Entity

@Entity(
    primaryKeys = [
        "contentFirstId","contentFavoriteId"
    ]
)
data class Favorite(
    val contentFirstId: Long,
    val contentFavoriteId: Long
)