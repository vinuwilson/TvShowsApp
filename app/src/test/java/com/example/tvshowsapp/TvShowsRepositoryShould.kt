package com.example.tvshowsapp

import com.example.tvshowsapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.verify

class TvShowsRepositoryShould : BaseUnitTest() {

    private val service: TvShowsService = mock()
    private val tvShowsList = mock<List<TvShowsData>>()
    private val exception = RuntimeException("Something went wrong")
    private val tvShowsDao : TvShowsDao = mock()

    @Test
    fun getTvShowsListFromService(): Unit = runBlocking {

        val repository = TvShowsRepository(service, tvShowsDao)

        repository.getTvShowsList().first()

        verify(service, times(1)).fetchTvShowsList()
    }

    @Test
    fun emitTvShowsListFromService(): Unit = runBlocking {

        val repository = mockSuccessfulCase()

        assertEquals(tvShowsList, repository.getTvShowsList().first().getOrNull())
    }

    @Test
    fun emitErrors(): Unit = runBlocking {

        val repository = mockFailureCase()

        assertEquals(exception.message, repository.getTvShowsList().first().exceptionOrNull()!!.message)
    }

    private suspend fun mockSuccessfulCase(): TvShowsRepository {
        whenever(service.fetchTvShowsList()).thenReturn(
            flow {
                emit(Result.success(tvShowsList))
            }
        )

        return TvShowsRepository(service, tvShowsDao)
    }

    private suspend fun mockFailureCase(): TvShowsRepository {
        whenever(service.fetchTvShowsList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        return TvShowsRepository(service, tvShowsDao)
    }
}