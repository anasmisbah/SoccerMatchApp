package com.example.soccermatch.ui.match.search

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.soccermatch.R
import com.example.soccermatch.ui.main.HomeActivity
import com.example.soccermatch.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchMatchActivityTest{
    @Rule
    @JvmField val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }


    @Test
    fun searchMatch(){
            onView(withId(R.id.nav_search)).perform(click())
            onView(isAssignableFrom(EditText::class.java)).perform(typeText("liverpool"),pressImeActionButton())
            onView(withId(R.id.rv_search_match)).check(matches(isDisplayed()))

    }
}