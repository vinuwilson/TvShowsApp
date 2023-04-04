package com.example.tvshowsapp

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TvShowsFeature {

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

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }
}