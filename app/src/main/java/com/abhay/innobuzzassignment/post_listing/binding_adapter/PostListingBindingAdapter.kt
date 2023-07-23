package com.abhay.innobuzzassignment.post_listing.binding_adapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhay.innobuzzassignment.RoomImpl.PostModel
import com.abhay.innobuzzassignment.post_listing.adapter.PostListingAdapter
import com.abhay.innobuzzassignment.post_listing.listener.IOnPostListingHandler
import com.abhay.innobuzzassignment.post_listing.viewmodel.PostListingViewModel
import com.abhay.innobuzzassignment.whatsapp_accessebility_service.WhatsAppAccessibilityService

object PostListingBindingAdapter {

    @JvmStatic
    @BindingAdapter("setPostList","lifeCycleOwner","iOnHandler")
    fun RecyclerView.setPostList(viewModel: PostListingViewModel, lifecycleOwner: LifecycleOwner,iOnHandler:IOnPostListingHandler){
        viewModel.postRoomListLiveData.observe(lifecycleOwner){ productList->
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = getAdapter(productList){
                iOnHandler.navigateToPostDetailsFragment(it)
            }
        }
    }


    fun getAdapter(postList : List<PostModel>, listener: (postMode: PostModel)->Unit): PostListingAdapter {
        return PostListingAdapter(postList,listener)
    }

    @JvmStatic
    @BindingAdapter("PermissionButtonListener")
    fun View.PermissionButtonListener(any:Any?){
        setOnClickListener {
            if (WhatsAppAccessibilityService.isAccessibilityServiceEnabled(context).not()){
                WhatsAppAccessibilityService.openAccessibilitySettings(context)
            }
        }
    }

}