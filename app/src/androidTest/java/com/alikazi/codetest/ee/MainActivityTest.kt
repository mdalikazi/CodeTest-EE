package com.alikazi.codetest.ee

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.alikazi.codetest.ee.main.MainActivity
import com.alikazi.codetest.ee.utils.MockDataHelper
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val mainActivityTestRule = IntentsTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResources() {
        IdlingRegistry.getInstance().register(mainActivityTestRule.activity.getIdlingResource())
    }

    @After
    fun unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(mainActivityTestRule.activity.getIdlingResource())
    }

    @Test
    fun sendMessage() {
        typeValidPhoneNumber()
        onView(withId(R.id.mainFragmentEditTextMessage)).perform(typeText(MockDataHelper.generateRandomString()))
        onView(withId(R.id.mainFragmentButtonSend)).perform(click())
        closeSoftKeyboard()
        val recyclerView = mainActivityTestRule.activity.findViewById<RecyclerView>(R.id.mainFragmentRecyclerView)
        assert(recyclerView.adapter != null && recyclerView.adapter?.itemCount == 1)
    }

    @Test
    fun checkResponse() {
        typeValidPhoneNumber()
        typeRandomMessage()
        pressSend()
        closeSoftKeyboard()
        waitForSeconds(1)
        val recyclerView = mainActivityTestRule.activity.findViewById<RecyclerView>(R.id.mainFragmentRecyclerView)
        assert(recyclerView.adapter != null && recyclerView.adapter?.itemCount == 2)
    }

    @Test
    fun checkValidation_invalidPhoneNumber_noMessage() {
        typeInvalidPhoneNumber()
        pressSend()
        onView(withId(R.id.mainFragmentNumberEditText)).check(matches(hasErrorText(getNumberInvalidError())))
    }

    @Test
    fun checkValidation_invalidPhoneNumber_withMessage() {
        typeInvalidPhoneNumber()
        typeRandomMessage()
        pressSend()
        onView(withId(R.id.mainFragmentNumberEditText)).check(matches(hasErrorText(getNumberInvalidError())))
    }

    private fun typeValidPhoneNumber() {
        onView(withId(R.id.mainFragmentNumberEditText)).perform(typeText(MockDataHelper.generateValidPhoneNumber()))
    }

    private fun typeInvalidPhoneNumber() {
        onView(withId(R.id.mainFragmentNumberEditText)).perform(typeText(MockDataHelper.generateInvalidPhoneNumber()))
    }

    private fun typeRandomMessage() {
        onView(withId(R.id.mainFragmentEditTextMessage)).perform(typeText(MockDataHelper.generateRandomString()))
    }

    private fun pressSend() {
        onView(withId(R.id.mainFragmentButtonSend)).perform(click())
    }

    private fun waitForSeconds(seconds: Int) {
        mainActivityTestRule.activity.runOnUiThread {
            Thread.sleep(seconds * 1000L)
        }
    }

    private fun getNumberInvalidError(): String {
        return mainActivityTestRule.activity.getString(R.string.validation_number_error_less_than_10_chars)
    }
}