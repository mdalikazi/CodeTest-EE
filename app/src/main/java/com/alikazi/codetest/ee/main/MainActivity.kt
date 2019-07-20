package com.alikazi.codetest.ee.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.alikazi.codetest.ee.R
import com.alikazi.codetest.ee.utils.Constants
import com.alikazi.codetest.ee.utils.InactivityHelper

class MainActivity : AppCompatActivity() {

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
        InactivityHelper.getInstance(this).stopCountdown()
    }

}
