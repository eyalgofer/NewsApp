package com.mmr.newsapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {

    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test before each test,
     * and close it after each test. This is a replacement for
     * [androidx.test.rule.ActivityTestRule].
     */
    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    // TODO: lets replace sleep with a smarter wait
    @Test
    fun enterArticleTest() {
        val scenario: ActivityScenario<MainActivity> = activityRule.getScenario()

        Thread.sleep(3000)

        onView(withId(R.id.news_list))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ArticlesAdapter.ViewHolder>(0, click()))

        Thread.sleep(8000)

        onView(withId(R.id.article_webview))
            .check(matches(isDisplayed()))
    }
}
