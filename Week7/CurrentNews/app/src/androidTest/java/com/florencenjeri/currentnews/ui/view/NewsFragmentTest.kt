package com.florencenjeri.currentnews.ui.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.florencenjeri.currentnews.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsFragmentTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    //Check that your app can be launched withut crashing
    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun onLaunchCheckRecyclerViewDataIsDisplayed() {
        //Given the main activity has launched
        ActivityScenario.launch(MainActivity::class.java)

        // When a view with id newsList is found
        onView(withId(R.id.contentMain))
            // Then check if it is being displayed on the screen
            .check(matches(isDisplayed()))
    }
}