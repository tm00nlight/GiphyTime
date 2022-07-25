package com.example.giphytime.api

import retrofit2.Call
import retrofit2.http.GET

interface GiphyApi {
    @GET("trending?api_key=gYlkN4KX6g7olKpv3YL7qIA8wr4j2uhR")
    fun fetchContents() : Call<GiphyItemResponse>
}