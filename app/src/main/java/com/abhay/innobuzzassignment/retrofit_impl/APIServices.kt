package com.abhay.innobuzzassignment.retrofit_impl

import com.abhay.innobuzzassignment.RoomImpl.PostModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getPost(): Call<List<PostModel>>
}