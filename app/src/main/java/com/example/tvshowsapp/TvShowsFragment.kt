package com.example.tvshowsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TvShowsFragment : Fragment() {

    private lateinit var viewModel: TvShowsViewModel
    lateinit var viewModelFactory: TvShowsViewModelFactory

    private val repository = TvShowsRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv_shows_list, container, false)

        setupViewModel()
        viewModel.tvShowsList.observe(this as LifecycleOwner) { tvShowsList ->
            if(tvShowsList.getOrNull() != null)
            setupTvList(view, tvShowsList.getOrNull()!!)
        }

        return view
    }

    private fun setupTvList(view: View?, tvShowsList: List<TvShowsData>) {
        with(view as RecyclerView) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = TvShowsRecyclerViewAdapter(tvShowsList)
        }
    }

    private fun setupViewModel() {
        viewModelFactory = TvShowsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[TvShowsViewModel::class.java]
    }

    companion object {

        @JvmStatic
        fun newInstance() = TvShowsFragment().apply {}
    }
}