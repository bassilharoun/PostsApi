package com.apps.apiposts;

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.apiposts.databinding.ActivityPostsListApiBinding
import com.apps.apiposts.service.RetrofitInstancePostListApi
import com.apps.apiposts.ui.utils.PostApiAdapter
import retrofit2.HttpException
import java.io.IOException

class PostsApiActicity : AppCompatActivity() {
        private lateinit var postsApiAdapter: PostApiAdapter
        private lateinit var binding: ActivityPostsListApiBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityPostsListApiBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setupRecyclerView()

            lifecycleScope.launchWhenCreated {
                binding.progressBarTodoApi.isVisible = true
                val response = try {
                    RetrofitInstancePostListApi.api.getPosts()

                }catch (e:IOException){
                    Log.d("postListApiActivity","Internet Error Connection")
                    binding.progressBarTodoApi.isVisible = false
                    return@launchWhenCreated
                }catch (e:HttpException){
                    Log.d("postListApiActivity","Unexpected Response")
                    binding.progressBarTodoApi.isVisible = false
                    return@launchWhenCreated
                }
                if (response.isSuccessful && response.body() != null){
                    postsApiAdapter.posts = response.body() !!
                }else{
                    Log.d("TodoListApiActivity","Unexpected Response")
                }
                binding.progressBarTodoApi.isVisible = false

            }

        }

        private fun setupRecyclerView() = binding.rvTodoListApi.apply {

            postsApiAdapter = PostApiAdapter()
            adapter = postsApiAdapter
            layoutManager = LinearLayoutManager(this@PostsApiActicity)
        }
    }

