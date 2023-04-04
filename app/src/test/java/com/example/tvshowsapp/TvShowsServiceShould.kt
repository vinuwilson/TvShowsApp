package com.example.tvshowsapp

import com.example.tvshowsapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class TvShowsServiceShould : BaseUnitTest() {

    private val api: TvShowsAPI = mock()
    private val tvShowsList: TvShowsList = mock()
    private val tvShowsData = tvShowsList.data

    @Test
    fun fetchTvShowsListFromAPI(): Unit = runBlocking {

        val service = TvShowsService(api)

        service.fetchTvShowsList().first()

        verify(api, times(1)).getTvShowsFromServer()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem(): Unit = runBlocking {

        val service = mockSuccessfulCase()

        assertEquals(Result.success(tvShowsData), service.fetchTvShowsList().first())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runBlocking {

        val service = mockFailureCase()

        assertEquals("Something went wrong", service.fetchTvShowsList().first().exceptionOrNull()?.message)
    }

    private fun mockSuccessfulCase(): TvShowsService {
        whenever(api.getTvShowsFromServer()).thenReturn(tvShowsList)

        return TvShowsService(api)
    }

    private fun mockFailureCase(): TvShowsService {
        whenever(api.getTvShowsFromServer()).thenThrow(RuntimeException("Damn backend developer"))

        return TvShowsService(api)
    }
}