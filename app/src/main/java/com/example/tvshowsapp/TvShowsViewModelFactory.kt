package com.example.tvshowsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class TvShowsViewModelFactory @Inject constructor(
    private val repository: TvShowsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TvShowsViewModel(repository) as T
    }
}
