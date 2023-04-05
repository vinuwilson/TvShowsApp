package com.example.tvshowsapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsapp.data.TvShowsData
import com.example.tvshowsapp.databinding.FragmentTvShowsBinding
import com.example.tvshowsapp.loadImage

class TvShowsRecyclerViewAdapter(
    private val values: List<TvShowsData>
) : RecyclerView.Adapter<TvShowsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentTvShowsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvShowTitle.text = item.title
        holder.tvShowImage.loadImage(item.cover_url)
        holder.releaseDate.text = item.release_date
        holder.directedBy.text = item.directed_by
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentTvShowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvShowTitle: TextView = binding.tvShowTitle
        val tvShowImage: ImageView = binding.tvShowImage
        val releaseDate: TextView = binding.releaseDate
        val directedBy: TextView = binding.directedBy
    }

}