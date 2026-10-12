package org.setu.placemark

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers.endsWith
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlacemarkFlowTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun startWithEmptyStore() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            AppData.placedMarks.findAll().map { it.id }.forEach { AppData.placedMarks.delete(it) }
        }
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun closeActivity() {
        scenario.close()
    }

    @Test
    fun createEditAndDeleteMark() {
        onView(withId(R.id.addMarkButton)).perform(click())
        fillForm("Home", "My home location", "10.5", "-20.3")
        onView(withId(R.id.saveButton)).perform(scrollTo(), click())
        onView(withText(endsWith(": Home"))).check(matches(isDisplayed()))

        onView(withId(R.id.editButton)).perform(scrollTo(), click())
        onView(withId(R.id.titleInput)).check(matches(withText("Home")))
        onView(withId(R.id.yInput)).check(matches(withText("-20.3")))
        fillForm("My Home", "Updated description", "-3.2", "42.0")
        onView(withId(R.id.saveButton)).perform(scrollTo(), click())
        onView(withText(endsWith(": My Home"))).check(matches(isDisplayed()))
        val mark = AppData.placedMarks.findAll().single()
        assertEquals(-3.2, mark.x, 0.0)
        assertEquals(42.0, mark.y, 0.0)

        onView(withId(R.id.deleteButton)).perform(scrollTo(), click())
        onView(withText(R.string.no_marks)).check(matches(isDisplayed()))
        assertEquals(0, AppData.placedMarks.findAll().size)
    }

    @Test
    fun invalidInputsKeepFormOpenWithoutCreatingMark() {
        onView(withId(R.id.addMarkButton)).perform(click())
        onView(withId(R.id.saveButton)).perform(scrollTo(), click())
        onView(withId(R.id.titleInput)).check(matches(hasErrorText("Title is required")))
        fillForm("Test", "Description", "", "1")
        onView(withId(R.id.saveButton)).perform(scrollTo(), click())
        onView(withId(R.id.xInput)).check(matches(hasErrorText("Enter a valid number")))
        fillForm("Test", "Description", "1", "")
        onView(withId(R.id.saveButton)).perform(scrollTo(), click())
        onView(withId(R.id.yInput)).check(matches(hasErrorText("Enter a valid number")))
        assertEquals(0, AppData.placedMarks.findAll().size)
        onView(withId(R.id.cancelButton)).perform(scrollTo(), click())
    }

    @Test
    fun cancellingFormDoesNotCreateMark() {
        onView(withId(R.id.addMarkButton)).perform(click())
        fillForm("Cancelled", "Not saved", "1", "2")
        onView(withId(R.id.cancelButton)).perform(scrollTo(), click())
        onView(withText(R.string.no_marks)).check(matches(isDisplayed()))
        assertEquals(0, AppData.placedMarks.findAll().size)
    }

    private fun fillForm(title: String, description: String, x: String, y: String) {
        onView(withId(R.id.titleInput)).perform(scrollTo(), replaceText(title))
        onView(withId(R.id.descriptionInput)).perform(scrollTo(), replaceText(description))
        onView(withId(R.id.xInput)).perform(scrollTo(), replaceText(x))
        onView(withId(R.id.yInput)).perform(scrollTo(), replaceText(y))
        closeSoftKeyboard()
    }
}
