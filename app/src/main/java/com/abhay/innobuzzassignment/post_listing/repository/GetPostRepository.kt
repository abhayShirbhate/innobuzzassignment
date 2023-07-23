package com.abhay.innobuzzassignment.post_listing.repository

import com.abhay.innobuzzassignment.RoomImpl.PostModel
import com.abhay.innobuzzassignment.post_listing.listener.GetPostAPIResponse
import com.abhay.innobuzzassignment.post_listing.listener.PostsRoomDBResponse


interface GetPostRepository {
    fun getPostFromAPI(getProductAPIResponse: GetPostAPIResponse)
    fun storePostsToRoomDB(list: List<PostModel>, listener:PostsRoomDBResponse)
    fun getPostsFromRoomDB(listener: PostsRoomDBResponse)
}