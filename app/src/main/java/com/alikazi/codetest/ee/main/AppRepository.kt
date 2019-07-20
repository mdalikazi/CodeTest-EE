package com.alikazi.codetest.ee.main

import android.os.Handler
import android.util.Log
import com.alikazi.codetest.ee.utils.Constants
import com.alikazi.codetest.ee.viewmodels.RequestResponseModels
import kotlin.random.Random

class AppRepository {

    fun sendMessageToServer(request: RequestResponseModels.MessageRequest): RequestResponseModels.MessageResponse {
        Log.d(Constants.LOG_TAG, "message sent to server: " + request.messageSent.text)
        val response = RequestResponseModels.MessageResponse()
        val received = MessageReceived(generateRandomString())
        Handler().postDelayed( {
            response._messageReceived.postValue(received)
//            response._networkErrors.postValue("Some exception")
        }, 1000)


        return response
    }

    private fun generateRandomString() : String {
        val length = Random.nextInt(2, 100)
        val alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz "
        return (1 .. length)
            .map { alphabets.random() }
            .joinToString("")
    }
}