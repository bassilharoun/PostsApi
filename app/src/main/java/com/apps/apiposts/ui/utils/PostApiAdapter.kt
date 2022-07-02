package com.apps.apiposts.ui.utils;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apps.apiposts.databinding.ItemPostBinding
import com.apps.apiposts.model.PostsApiData


class PostApiAdapter : RecyclerView.Adapter<PostApiAdapter.PostsApiViewHolder>() {


        inner class PostsApiViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

private val diffCallBack = object : DiffUtil.ItemCallback<PostsApiData>(){
        override fun areItemsTheSame(oldItem: PostsApiData, newItem: PostsApiData): Boolean {
        return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostsApiData, newItem: PostsApiData): Boolean {
        return oldItem == newItem
        }

        }
private val differ = AsyncListDiffer(this,diffCallBack)
        var posts : List<PostsApiData>
            get() = differ.currentList
                    set(value) { differ.submitList(value) }

                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsApiViewHolder {
                    return PostsApiViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false))
                    }

                    override fun onBindViewHolder(holder: PostsApiViewHolder, position: Int) {

                    holder.binding.apply {
                    val post = posts[position]
                    tvPost.text = post.body
                            tvTitle.text = post.title
                    }

                    }

                    override fun getItemCount() = posts.size



                    }
