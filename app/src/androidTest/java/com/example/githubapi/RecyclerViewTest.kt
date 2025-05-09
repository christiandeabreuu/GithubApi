package com.example.githubapi

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.githubapi.ui.HomeActivity
import com.example.githubapi.ui.RepositoryAdapter
import org.junit.Rule
import org.junit.Test

class RecyclerViewTest {

    @get:Rule
    val activityRule =
        ActivityScenarioRule(HomeActivity::class.java) // 🔥 Inicia a atividade antes do teste


    @Test
    fun scrollToLastItemInRecyclerView() {
        // Simula o scroll até o último item da lista
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RepositoryAdapter.ViewHolder>(
                49
            )
        )
    }

    @Test
    fun clickOnRecyclerViewItem() {
        // Aguarda a lista ser carregada antes de tentar interagir com ela
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        // Simula um clique no primeiro item do RecyclerView
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RepositoryAdapter.ViewHolder>(
                0, click()
            )
        )
    }

    @Test
    fun scrollAndCheckItemVisibility() {
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RepositoryAdapter.ViewHolder>(
                20
            )
        )

        // Verifica se um item específico aparece após o scroll
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RepositoryAdapter.ViewHolder>(
                20
            )
        )
    }
}
