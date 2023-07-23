package com.abhay.innobuzzassignment.RoomImpl

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "posts")
data class PostModel(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) : Serializable