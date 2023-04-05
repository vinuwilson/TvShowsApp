package com.example.tvshowsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import javax.inject.Inject

class TvShowsViewModel @Inject constructor(
    private val repository: TvShowsRepository
) : ViewModel() {

    val tvShowsList = liveData {
        emitSource(repository.getTvShowsList().asLiveData())
    }
}
