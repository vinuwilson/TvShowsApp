package com.example.tvshowsapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class TvShowsRepository(
    private val service: TvShowsService
) {

    suspend fun getTvShowsList() : Flow<Result<List<TvShowsData>>> {
        return service.fetchTvShowsList()
    }

}
