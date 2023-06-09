package com.example.tvshowsapp.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

open class BaseUnitTest {

    @ExperimentalCoroutinesApi
    @get: Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get: Rule
    var instantTaskExecutor = InstantTaskExecutorRule()
}