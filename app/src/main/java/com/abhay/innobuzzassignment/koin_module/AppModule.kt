package com.abhay.innobuzzassignment.koin_module

import com.abhay.innobuzzassignment.RoomImpl.PostDatabase
import com.abhay.innobuzzassignment.post_details.viewmodel.PostDetailsViewModel
import com.abhay.innobuzzassignment.post_listing.repository.GetPostRepository
import com.abhay.innobuzzassignment.post_listing.repository.GetProductsRepositoryImpl
import com.abhay.innobuzzassignment.post_listing.viewmodel.PostListingViewModel
import com.abhay.innobuzzassignment.retrofit_impl.RetrofitInstance
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        RetrofitInstance.getRetrofitInstance()
    }

    single<PostDatabase> {
        PostDatabase.getPostDatabase(get())
    }

    single<GetPostRepository> {
        GetProductsRepositoryImpl(get(),get())
    }

    viewModel { PostListingViewModel(get()) }
    viewModel { PostDetailsViewModel() }

}