package com.abhay.innobuzzassignment.post_listing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhay.innobuzzassignment.R
import com.abhay.innobuzzassignment.RoomImpl.PostModel
import com.abhay.innobuzzassignment.databinding.PostAdapterItemBinding
import com.abhay.innobuzzassignment.post_listing.model.Post

class PostListingAdapter(private val postList: List<PostModel>, private val listener: (postMode: PostModel)->Unit) :
    RecyclerView.Adapter<PostListingAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_adapter_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val postModel = postList[position]
        holder.bind(postModel)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = PostAdapterItemBinding.bind(itemView)
        init {
            binding.root.setOnClickListener {
                listener(postList[adapterPosition])
            }
        }

        fun bind(post: PostModel) {
            binding.post = Post.getPost(post)
        }
    }
}
