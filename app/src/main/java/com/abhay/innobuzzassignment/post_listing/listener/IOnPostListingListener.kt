package com.abhay.innobuzzassignment.post_listing.listener

import com.abhay.innobuzzassignment.RoomImpl.PostModel

interface IOnPostListingListener {
    fun navigateToPostDetailsFragment(postModel: PostModel)
}