package com.example.giphytime.api

import com.example.giphytime.GiphyItem
import com.google.gson.annotations.SerializedName

class GiphyItemResponse {
    @SerializedName("data")
    lateinit var giphyItems : List<GiphyItem>
}