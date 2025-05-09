package com.example.githubapi

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.githubapi.ui.HomeActivity
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun verifyTitleAndRecyclerViewAreDisplayed() {
        // Verifica se o título "Top Repositórios" está visível
        onView(withId(R.id.title)).check(matches(isDisplayed()))

        // Verifica se o RecyclerView está na tela
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}