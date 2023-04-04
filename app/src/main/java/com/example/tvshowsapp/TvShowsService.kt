package com.example.tvshowsapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


class TvShowsService(
    private val api: TvShowsAPI
) {

   suspend fun fetchTvShowsList() : Flow<Result<List<TvShowsData>>> {
        return flow {
            emit(Result.success((api.getTvShowsFromServer()).data))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
