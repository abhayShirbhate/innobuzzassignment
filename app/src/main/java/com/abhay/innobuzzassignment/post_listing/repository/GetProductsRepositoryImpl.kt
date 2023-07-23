package com.abhay.innobuzzassignment.post_listing.repository

import com.abhay.innobuzzassignment.RoomImpl.PostDatabase
import com.abhay.innobuzzassignment.RoomImpl.PostModel
import com.abhay.innobuzzassignment.post_listing.listener.GetPostAPIResponse
import com.abhay.innobuzzassignment.post_listing.listener.PostsRoomDBResponse
import com.abhay.innobuzzassignment.post_listing.response.GetPostAPIRespondState
import com.abhay.innobuzzassignment.retrofit_impl.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class GetProductsRepositoryImpl(private val api: ApiService,private val roomDB:PostDatabase): GetPostRepository {

    override fun getPostFromAPI(getProductAPIResponse: GetPostAPIResponse) {
        api.getPost().enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                if (response.isSuccessful && response.body() != null){
                    getProductAPIResponse.getPostSuccessAPIListener(GetPostAPIRespondState.Success(response.body()!!))
                }else getProductAPIResponse.getPostFailureAPIListener(GetPostAPIRespondState.Error("NA"))

            }
            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                if (t is SocketTimeoutException){
                    getProductAPIResponse.getPostFailureAPIListener(GetPostAPIRespondState.InternetError(t.message?:"NA"))
                }else getProductAPIResponse.getPostFailureAPIListener(GetPostAPIRespondState.Error(t.message?:"NA"))
            }
        })
    }

    override fun storePostsToRoomDB(list: List<PostModel>, listener: PostsRoomDBResponse) {
        val resp = roomDB.postDao().insertAll(list)
        if(resp.isNotEmpty()){
            listener.storePostsRoomDBSuccessResponse("Inserted all item successfully")
        }else{
            listener.storePostsRoomDBFailureResponse("Failed to insert item")
        }
    }

    override fun getPostsFromRoomDB(listener: PostsRoomDBResponse) {
        val list = roomDB.postDao().getAllPosts()
        if(list.isNotEmpty()){
            listener.getPostsRoomDBSuccessResponse(list)
        }else{
            listener.getPostsRoomDBFailureResponse("Failed to get posts list")
        }
    }
}