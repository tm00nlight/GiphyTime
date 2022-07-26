package com.example.giphytime

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

private const val TAG = "GiphyItemFragment"
private const val POSITION = "GiphyNumber"

class GiphyItemFragment : Fragment() {
    private val giphyViewModel: GiphyViewModel by lazy {
        ViewModelProvider(this).get(GiphyViewModel::class.java) }
    private lateinit var giphyItemSelected: GiphyItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_giphy_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = this.requireArguments().getInt(POSITION)
        Log.d(TAG, "getInt from Bundle is $position")

        val imageView = view.findViewById<ImageView>(R.id.giphyImage)
        val textView = view.findViewById<TextView>(R.id.giphyTitle)

        giphyViewModel.giphyItemLiveData.observe(
            viewLifecycleOwner, Observer { giphyItems ->
                giphyItemSelected = giphyItems[position]

            bindDrawables(giphyItemSelected.images.original.url, imageView)
            textView.setText(giphyItemSelected.title)
            }
        )

    }

    fun bindDrawables(url : String, view: ImageView) {
        Glide
            .with(requireContext())
            .load(url)
            .placeholder(R.drawable.giphy_loading_light)
            .into(view)
    }

    companion object {
        fun newInstance(position: Int) = GiphyItemFragment().apply {
            arguments = Bundle().apply { putInt(POSITION, position)
                Log.d(TAG, "putInt to Bundle is $position")}
        }
    }
}