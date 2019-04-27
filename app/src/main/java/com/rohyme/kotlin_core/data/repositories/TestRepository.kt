package com.rohyme.kotlin_core.data.repositories

import com.rohyme.kotlin_core.data.remote.ApiService
import com.rohyme.kotlin_core.data.remote.requests.PostResponse
import kotlinx.coroutines.Deferred

/**
 *
 * @Auther Rohyme
 */

class TestRepository (private val apiService: ApiService){

    suspend fun getPosts(): Deferred<List<PostResponse>> {
        return apiService.getPosts()
    }
}