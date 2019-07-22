package com.alikazi.codetest.ee.utils

import android.app.Activity
import android.app.AlertDialog
import android.os.Handler
import android.util.Log
import com.alikazi.codetest.ee.R
import java.util.concurrent.TimeUnit

class InactivityTimer(private val activity: Activity?) : Runnable {

    private val handler = Handler()

    companion object {
        private var INSTANCE: InactivityTimer? = null

        fun getInstance(activity: Activity?): InactivityTimer =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: InactivityTimer(activity)
            }.also { INSTANCE = it }
    }

    fun startFourSecondsCountdown() {
        handler.postDelayed(this, TimeUnit.SECONDS.toMillis(Constants.INACTIVITY_COUNTDOWN_DELAY_SECONDS))
    }

    fun stopCountdown() {
        handler.removeCallbacks(this)
    }

    override fun run() {
        if (activity != null && !activity.isFinishing) {
            Log.d(Constants.LOG_TAG, "(activity != null && !activity.isFinishing")
            AlertDialog.Builder(activity)
                .setTitle(activity.getString(R.string.main_fragment_inactive_alert_title))
                .setMessage(activity.getString(R.string.main_fragment_inactive_alert_message))
                .setPositiveButton(activity.getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        } else {
            Log.d(Constants.LOG_TAG, "activity != null " + (activity != null))
            Log.d(Constants.LOG_TAG, "activity.isFinishing " + (activity?.isFinishing))
        }
    }
}