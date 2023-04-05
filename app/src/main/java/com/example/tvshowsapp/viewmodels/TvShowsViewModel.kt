package com.example.tvshowsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.tvshowsapp.repository.TvShowsRepository
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TvShowsViewModel @Inject constructor(
    private val repository: TvShowsRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val tvShowsList = liveData {
        loader.value = true
        emitSource(repository.getTvShowsList().onEach {
            loader.value = false
        }.asLiveData())
    }
}
