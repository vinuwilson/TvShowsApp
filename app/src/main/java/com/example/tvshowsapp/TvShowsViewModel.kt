package com.example.tvshowsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData

class TvShowsViewModel(
    private val repository: TvShowsRepository
) : ViewModel() {


    val tvShowsList = liveData {
        emitSource(repository.getTvShowsList().asLiveData())
    }
}
