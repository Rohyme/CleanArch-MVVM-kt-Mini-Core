package com.rohyme.kotlin_core.data.remote

import com.rohyme.kotlin_core.data.remote.requests.PostResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 *
 * @Auther Rohyme
 */
interface ApiService {

    @GET("demo/posts")
    fun getPosts():Deferred<List<PostResponse>>
}