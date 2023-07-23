package com.abhay.innobuzzassignment.post_listing.listener

import com.abhay.innobuzzassignment.RoomImpl.PostModel

interface IOnPostListingHandler {
    fun navigateToPostDetailsFragment(postModel: PostModel)
}