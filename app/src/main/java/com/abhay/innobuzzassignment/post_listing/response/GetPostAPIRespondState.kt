package com.abhay.innobuzzassignment.post_listing.response

import com.abhay.innobuzzassignment.RoomImpl.PostModel

sealed class GetPostAPIRespondState{
    data class Success(val apiResponseList: List<PostModel>) : GetPostAPIRespondState()
    data class Error(val errorMessage: String) : GetPostAPIRespondState()
    data class InternetError(val errorMessage: String) : GetPostAPIRespondState()
}
