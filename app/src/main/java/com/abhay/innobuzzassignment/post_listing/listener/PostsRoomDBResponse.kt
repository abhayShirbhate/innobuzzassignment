package com.abhay.innobuzzassignment.post_listing.listener

import com.abhay.innobuzzassignment.RoomImpl.PostModel

interface PostsRoomDBResponse {
    fun storePostsRoomDBSuccessResponse(message:String)
    fun storePostsRoomDBFailureResponse(error:String)
    fun getPostsRoomDBSuccessResponse(list: List<PostModel>)
    fun getPostsRoomDBFailureResponse(error:String)
}