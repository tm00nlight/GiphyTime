package com.example.giphytime

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.giphytime.api.GiphyApi
import com.example.giphytime.api.GiphyItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "GiphyFetchr"

class GiphyFetchr {
    private val giphyApi: GiphyApi

    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/gifs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        giphyApi = retrofit.create(GiphyApi::class.java)
    }

    fun fetchContents() : LiveData<List<GiphyItem>> {
        val responseLiveData : MutableLiveData<List<GiphyItem>> = MutableLiveData()

        val giphyRequest : Call<GiphyItemResponse> = giphyApi.fetchContents()
        giphyRequest.enqueue(object : Callback<GiphyItemResponse> {
            override fun onFailure(call: Call<GiphyItemResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch data", t)
            }

            override fun onResponse(call: Call<GiphyItemResponse>, response: Response<GiphyItemResponse>) {
                Log.d(TAG, "Response received")
                val giphyItemResponse : GiphyItemResponse? = response.body()
                val giphyItems : List<GiphyItem> = giphyItemResponse?.giphyItems?: mutableListOf()
                responseLiveData.value = giphyItems
                Log.d(TAG, "Response consists of ${giphyItems.size} gifs")
            }
        })

        return responseLiveData
    }
}