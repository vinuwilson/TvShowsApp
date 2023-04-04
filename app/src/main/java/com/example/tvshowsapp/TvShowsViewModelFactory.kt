package com.example.tvshowsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TvShowsViewModelFactory(
    private val repository: TvShowsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TvShowsViewModel(repository) as T
    }
}
