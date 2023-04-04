package com.example.tvshowsapp

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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TvShowsFragment : Fragment() {

    private lateinit var viewModel: TvShowsViewModel
    lateinit var viewModelFactory: TvShowsViewModelFactory

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://mcuapi.herokuapp.com/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val api = retrofit.create(TvShowsAPI::class.java)
    private val service = TvShowsService(api)
    private val repository = TvShowsRepository(service)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv_shows_list, container, false)

        setupViewModel()
        observeListData()

        return view
    }

    private fun observeListData() {
        viewModel.tvShowsList.observe(this as LifecycleOwner) { tvShowsList ->
            if (tvShowsList.getOrNull() != null)
                setupTvList(view, tvShowsList.getOrNull()!!)
            else
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }
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