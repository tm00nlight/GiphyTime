package com.example.giphytime

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val TAG = "GiphyExplorerFragment"

class GiphyExplorerFragment : Fragment() {
    private lateinit var giphyRecyclerView: RecyclerView
    private lateinit var giphyViewModel: GiphyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        giphyViewModel = ViewModelProvider(this).get(GiphyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_giphy_explorer, container, false)
        giphyRecyclerView = view.findViewById(R.id.giphy_recycler_view)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            giphyRecyclerView.layoutManager = GridLayoutManager(context, 3)
        else
            giphyRecyclerView.layoutManager = GridLayoutManager(context, 2)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        giphyViewModel.giphyItemLiveData.observe(
            viewLifecycleOwner, Observer { giphyItems ->
                giphyRecyclerView.adapter = GIFAdapter(giphyItems)
            }
        )
    }

    companion object {
        fun newInstance() = GiphyExplorerFragment()
    }

    private inner class GIFHolder(itemImageView : ImageView) :
        RecyclerView.ViewHolder(itemImageView) {

        val imageView = itemImageView

        fun bindDrawables(url : String) {
            Glide
                .with(requireContext())
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.giphy_loading_light)
                .into(imageView)
        }

    }

    private inner class GIFAdapter(private val giphyItems : List<GiphyItem>) :
        RecyclerView.Adapter<GIFHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GIFHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_gif, parent, false) as ImageView
            return GIFHolder(view)
        }

        override fun getItemCount(): Int {
            return giphyItems.size
        }

        override fun onBindViewHolder(holder: GIFHolder, position: Int) {
            val giphyItem = giphyItems[position]
            holder.imageView.setOnClickListener {
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, GiphyItemFragment.newInstance(position))
                    .addToBackStack(null)
                    .commit()
            }
            holder.bindDrawables(giphyItem.images.fixed_height.url)
        }

    }

}

