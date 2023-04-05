package com.example.tvshowsapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TvShowsRepository @Inject constructor(
    private val service: TvShowsService,
    private val tvShowsDao: TvShowsDao
) {

    suspend fun getTvShowsList(): Flow<Result<List<TvShowsData>>> {
        return flow {
            if (service.fetchTvShowsList().first().isFailure) {
                if (tvShowsDao.getAllRecords().isNotEmpty())
                    emit(Result.success(tvShowsDao.getAllRecords()))
                else
                    emit(Result.failure(RuntimeException("Something went wrong")))
            } else {
                emit(service.fetchTvShowsList().first())
                insertDataIntoDB(service.fetchTvShowsList().first().getOrNull()!!)
            }
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
//        return service.fetchTvShowsList()
    }

    private suspend fun insertDataIntoDB(tvShowsData: List<TvShowsData>) {
        withContext(Dispatchers.IO) {
            tvShowsDao.deleteAllRecords()
            tvShowsDao.insertRecord(tvShowsData)
        }
    }

}
