package com.abhay.innobuzzassignment.post_details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abhay.innobuzzassignment.RoomImpl.PostModel
import com.abhay.innobuzzassignment.databinding.PostDetailsFragmentBinding
import com.abhay.innobuzzassignment.post_details.viewmodel.PostDetailsViewModel
import com.abhay.innobuzzassignment.post_listing.model.Post
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostDetailsFragment: Fragment() {
    lateinit var binding: PostDetailsFragmentBinding
    val viewModel by viewModel<PostDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostDetailsFragmentBinding.inflate(inflater,container,false)
        initBinding()

        return binding.root
    }
    private fun initBinding(){
        viewModel.postModel = Post.getPost(requireArguments().getSerializable(POST_MODEL_DATA_KEY) as PostModel)
        binding.post = viewModel.postModel
    }

    companion object{
        const val POST_MODEL_DATA_KEY = "postModelDataKey"
    }
}