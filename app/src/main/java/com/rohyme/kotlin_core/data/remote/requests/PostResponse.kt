package com.rohyme.kotlin_core.data.remote.requests

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String = ""
)