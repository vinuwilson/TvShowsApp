package com.example.tvshowsapp

import com.example.tvshowsapp.utils.BaseUnitTest
import com.example.tvshowsapp.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.verify


class TvShowsViewModelShould : BaseUnitTest() {

    private val repository: TvShowsRepository = mock()
    private val tvShowsList = mock<List<TvShowsData>>()
    private val expected = Result.success(tvShowsList)
    private val exception = RuntimeException("Something went wrong")


    @Test
    fun getTvListFromRepository(): Unit = runBlocking {
        val viewModel = mockSuccessfulCase()

        viewModel.tvShowsList.getValueForTest()

        verify(repository, times(1)).getTvShowsList()
    }

    @Test
    fun emitTvShowListFromRepository() = runBlocking {
        val viewModel = mockSuccessfulCase()

        assertEquals(expected, viewModel.tvShowsList.getValueForTest())

    }

    @Test
    fun emitErrorWhenReceiveError() {
        val viewModel = mockFailureCase()

        assertEquals(exception, viewModel.tvShowsList.getValueForTest()!!.exceptionOrNull())
    }

    private suspend fun mockSuccessfulCase(): TvShowsViewModel {
        runBlocking {
            whenever(repository.getTvShowsList()).thenReturn(flow {
                emit(expected)
            })
        }
        return TvShowsViewModel((repository))
    }


    private fun mockFailureCase(): TvShowsViewModel {
        runBlocking {
            whenever(repository.getTvShowsList()).thenReturn(flow {
                emit(Result.failure(exception))
            })
        }

        return TvShowsViewModel(repository)
    }
}