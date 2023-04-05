package com.example.tvshowsapp

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TvShowsRepository @Inject constructor(
    private val service: TvShowsService
) {

    suspend fun getTvShowsList() : Flow<Result<List<TvShowsData>>> {
        return service.fetchTvShowsList()
    }

}
