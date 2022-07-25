package com.example.giphytime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

private const val TAG = "GiphyItemFragment"

class GiphyItemFragment : Fragment() {
    private lateinit var giphyViewModel: GiphyViewModel
    private lateinit var giphyItemSelected : GiphyItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //giphyViewModel = ViewModelProvider(this).get(GiphyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_giphy_item, container, false)
    }

    companion object {
        fun newInstance() = GiphyItemFragment()
    }
}