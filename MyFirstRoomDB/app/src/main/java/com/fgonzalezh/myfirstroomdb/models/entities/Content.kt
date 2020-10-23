package com.fgonzalezh.myfirstroomdb.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Content (
    @PrimaryKey(autoGenerate = true)
    val id: Long?=null,
    val name: String,
    val lastName: String,
    val age: Int
){
    var fullName = "$name $lastName"

    override fun equals(other: Any?): Boolean {
        if (other is Content)
            return other.id==this.id
        return false
    }
}