package com.abhay.innobuzzassignment.post_listing.model

import com.abhay.innobuzzassignment.RoomImpl.PostModel


class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String){
    companion object{
        fun getPost(postModel: PostModel): Post{
            return Post(
                postModel.id,
                postModel.userId,
                postModel.title,
                postModel.body
            )
        }
    }
}