package com.alikazi.codetest.ee.main

import android.os.Bundle
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.test.espresso.IdlingResource
import com.alikazi.codetest.ee.R
import com.alikazi.codetest.ee.utils.Constants
import com.alikazi.codetest.ee.utils.IdlingResourcesHelper
import com.alikazi.codetest.ee.utils.InactivityTimer

class MainActivity : AppCompatActivity() {

    @VisibleForTesting
    fun getIdlingResource(): IdlingResource {
        return IdlingResourcesHelper.getIdlingResource()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, MainFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commitNow()
        }
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        Log.i(Constants.LOG_TAG, "onUserInteraction")
        InactivityTimer.getInstance(this).stopCountdown()
    }

}
