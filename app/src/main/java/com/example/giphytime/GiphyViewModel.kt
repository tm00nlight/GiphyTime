package com.example.giphytime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GiphyViewModel : ViewModel() {
    val giphyItemLiveData: LiveData<List<GiphyItem>>

    init {
        giphyItemLiveData = GiphyFetchr().fetchContents()
    }
}