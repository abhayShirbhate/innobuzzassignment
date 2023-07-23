package com.abhay.innobuzzassignment.post_listing.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhay.innobuzzassignment.RoomImpl.PostModel
import com.abhay.innobuzzassignment.post_listing.listener.GetPostAPIResponse
import com.abhay.innobuzzassignment.post_listing.listener.IOnPostListingListener
import com.abhay.innobuzzassignment.post_listing.repository.GetPostRepository
import com.abhay.innobuzzassignment.post_listing.response.GetPostAPIRespondState
import com.abhay.innobuzzassignment.post_listing.listener.IOnPostListingHandler
import com.abhay.innobuzzassignment.post_listing.listener.PostsRoomDBResponse
import com.abhay.innobuzzassignment.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostListingViewModel(private val repository: GetPostRepository): ViewModel(),GetPostAPIResponse,PostsRoomDBResponse,
    IOnPostListingHandler {
    lateinit var iOnListener: IOnPostListingListener

    private var _postAPIListLiveData = MutableLiveData<List<PostModel>>()
    val postAPIListLiveData get() = _postAPIListLiveData

    private var _postRoomListLiveData = MutableLiveData<List<PostModel>>()
    val postRoomListLiveData get() = _postRoomListLiveData

    private var _getProductAPIErrorLiveData = MutableLiveData<String>()
    val getPostAPIErrorLiveData get() = _getProductAPIErrorLiveData

    val permissionButtonText = ObservableField(Utils.REQUEST_ACCESSIBILITY_PERMISSION_TEXT)


    fun getPostsFromAPI(){
        repository.getPostFromAPI(this)
    }
    fun storePostsToRoomDB(){
        viewModelScope.launch(Dispatchers.IO) {
            postAPIListLiveData.value?.let { repository.storePostsToRoomDB(it,this@PostListingViewModel) }
        }
    }
    fun getPostsFromRoomDB(){
        repository.getPostsFromRoomDB(this)
    }

    fun setPostAPIListLiveDataObserver(lifecycleOwner: LifecycleOwner){
        postAPIListLiveData.observe(lifecycleOwner){
            storePostsToRoomDB()
        }
    }

    override fun navigateToPostDetailsFragment(postModel: PostModel) {
        iOnListener.navigateToPostDetailsFragment(postModel)
    }

    override fun getPostSuccessAPIListener(response: GetPostAPIRespondState.Success) {
        postAPIListLiveData.postValue(response.apiResponseList)

    }

    override fun getPostFailureAPIListener(response: GetPostAPIRespondState) {
        if (response is GetPostAPIRespondState.InternetError){
            getPostAPIErrorLiveData.postValue("Please Check Internet Connection, Please try again")
        } else getPostAPIErrorLiveData.postValue("Something went wrong, Please try again")
    }

    override fun storePostsRoomDBSuccessResponse(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPostsFromRoomDB()
        }
    }

    override fun storePostsRoomDBFailureResponse(error: String) {
        getPostAPIErrorLiveData.postValue("Failed while storing posts data to RoomDB")
    }

    override fun getPostsRoomDBSuccessResponse(list: List<PostModel>) {
        postRoomListLiveData.postValue(list)
    }

    override fun getPostsRoomDBFailureResponse(error: String) {
        getPostAPIErrorLiveData.postValue("Failed while fetching posts data from RoomDB")
    }


}