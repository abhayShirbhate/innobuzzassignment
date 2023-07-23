package com.abhay.innobuzzassignment.post_listing.listener

import com.abhay.innobuzzassignment.post_listing.response.GetPostAPIRespondState

interface GetPostAPIResponse {
    fun getPostSuccessAPIListener(response : GetPostAPIRespondState.Success)
    fun getPostFailureAPIListener(response : GetPostAPIRespondState)
}