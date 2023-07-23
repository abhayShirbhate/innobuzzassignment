package com.abhay.innobuzzassignment.post_listing.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abhay.innobuzzassignment.R
import com.abhay.innobuzzassignment.RoomImpl.PostModel
import com.abhay.innobuzzassignment.databinding.PostListingFragmentBinding
import com.abhay.innobuzzassignment.post_details.fragment.PostDetailsFragment
import com.abhay.innobuzzassignment.post_listing.listener.IOnPostListingListener
import com.abhay.innobuzzassignment.post_listing.viewmodel.PostListingViewModel
import com.abhay.innobuzzassignment.utils.Utils
import com.abhay.innobuzzassignment.whatsapp_accessebility_service.WhatsAppAccessibilityService
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostListingFragment:Fragment(), IOnPostListingListener {
    val viewModel by viewModel<PostListingViewModel>()
    lateinit var binding: PostListingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostListingFragmentBinding.inflate(inflater,container,false)
        initBinding()
        return binding.root
    }

    private fun initBinding(){
        viewModel.iOnListener = this
        binding.viewModel = viewModel
        binding.iOnHandler = viewModel
        binding.lifeCycleOwner = this
        viewModel.setPostAPIListLiveDataObserver(viewLifecycleOwner)
        addGetProductAPIErrorObservable()
        viewModel.getPostsFromAPI()
    }

    private fun addGetProductAPIErrorObservable(){
        viewModel.getPostAPIErrorLiveData.observe(viewLifecycleOwner){
            showDialogMessage(it)
        }
    }

    fun showDialogMessage(error: String) {
        Utils.showMessageDialog(requireContext(),"Alert",error){}
    }

    override fun onResume() {
        super.onResume()
        if(WhatsAppAccessibilityService.isAccessibilityServiceEnabled(requireContext())){
            viewModel.permissionButtonText.set(Utils.ACCESSIBILITY_PERMISSION_ACCEPTED_TEXT)

            val intent = Intent(requireContext(),WhatsAppAccessibilityService::class.java)
            requireActivity().startService(intent)
        }else viewModel.permissionButtonText.set(Utils.REQUEST_ACCESSIBILITY_PERMISSION_TEXT)
    }

    override fun navigateToPostDetailsFragment(postModel: PostModel) {
        val bundle = Bundle().apply {
            putSerializable(PostDetailsFragment.POST_MODEL_DATA_KEY,postModel)
        }
        findNavController().navigate(R.id.postDetailsFragment,bundle)
    }
}