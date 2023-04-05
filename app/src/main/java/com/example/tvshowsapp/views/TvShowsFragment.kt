package com.example.tvshowsapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsapp.*
import com.example.tvshowsapp.data.TvShowsData
import com.example.tvshowsapp.viewmodels.TvShowsViewModel
import com.example.tvshowsapp.viewmodels.TvShowsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TvShowsFragment : Fragment() {

    lateinit var viewModel: TvShowsViewModel
    @Inject
    lateinit var viewModelFactory: TvShowsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv_shows_list, container, false)

        setupViewModel()
        setupLoaderView(view.findViewById(R.id.loader))
        observeListData(view.findViewById(R.id.tv_shows_list))

        return view
    }

    private fun setupLoaderView(loader: View) {
        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when(loading){
                true -> loader.visibility = View.VISIBLE
                else -> loader.visibility = View.GONE
            }
        }
    }

    private fun observeListData(view: View) {
        viewModel.tvShowsList.observe(this as LifecycleOwner) { tvShowsList ->
            if (tvShowsList.getOrNull() != null)
                setupTvList(view, tvShowsList.getOrNull()!!)
            else
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupTvList(view: View, tvShowsList: List<TvShowsData>) {
        with(view as RecyclerView) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = TvShowsRecyclerViewAdapter(tvShowsList)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[TvShowsViewModel::class.java]
    }

}