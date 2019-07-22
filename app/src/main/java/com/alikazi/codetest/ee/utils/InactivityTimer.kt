package com.alikazi.codetest.ee.utils

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import com.alikazi.codetest.ee.R
import java.util.concurrent.TimeUnit

class InactivityTimer(private val context: Context?) : Runnable {

    private val handler = Handler()

    companion object {
        private var INSTANCE: InactivityTimer? = null

        fun getInstance(context: Context?): InactivityTimer =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: InactivityTimer(context)
            }.also { INSTANCE = it }
    }

    fun startFourSecondsCountdown() {
        handler.postDelayed(this, TimeUnit.SECONDS.toMillis(Constants.INACTIVITY_COUNTDOWN_DELAY_SECONDS))
    }

    fun stopCountdown() {
        handler.removeCallbacks(this)
    }

    override fun run() {
        AlertDialog.Builder(context)
            .setTitle(context?.getString(R.string.main_fragment_inactive_alert_title))
            .setMessage(context?.getString(R.string.main_fragment_inactive_alert_message))
            .setPositiveButton(context?.getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}