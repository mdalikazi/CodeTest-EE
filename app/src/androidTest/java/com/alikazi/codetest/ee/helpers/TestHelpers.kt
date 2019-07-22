package com.alikazi.codetest.ee.helpers

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import java.util.concurrent.TimeUnit

object TestHelpers {

    fun waitForSeconds(seconds: Long): ViewAction {
        return object: ViewAction {
            override fun getDescription(): String {
                return "wait for $seconds seconds"
            }

            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isRoot()
            }

            override fun perform(uiController: UiController?, view: View?) {
                uiController?.loopMainThreadForAtLeast(TimeUnit.SECONDS.toMillis(seconds))
            }
        }
    }

}