package com.example.tvshowsapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.example.tvshowsapp.utils.BaseUITest
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TvShowsFeature : BaseUITest(){

    val mActivity = ActivityScenarioRule(MainActivity::class.java)
        @Rule get

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.tvshowsapp", appContext.packageName)
    }

    @Test
    fun displayScreenTitle() {
        assertDisplayed("TV Shows List")
    }

    @Test
    fun displayListOfTvShows() {

        Thread.sleep(4000)

        assertRecyclerViewItemCount(R.id.tv_shows_list,17)

        onView(
            allOf(
                withId(R.id.tv_show_title),
                isDescendantOfA(nthChildOf(withId(R.id.tv_shows_list), 0))
            )
        )
            .check(matches(withText("WandaVision")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.release_date),
                isDescendantOfA(nthChildOf(withId(R.id.tv_shows_list), 0))
            )
        )
            .check(matches(withText("2021-01-15")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.directed_by),
                isDescendantOfA(nthChildOf(withId(R.id.tv_shows_list), 0))
            )
        )
            .check(matches(withText("Matt Shakman")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayProgressLoaderWhileFetchingTvShowsList() {

        assertDisplayed(R.id.loader)
    }

    @Test
    fun hideLoader() {

        Thread.sleep(2000)

        assertNotDisplayed(R.id.loader)
    }
}