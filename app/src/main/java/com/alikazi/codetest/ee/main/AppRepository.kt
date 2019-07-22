package com.alikazi.codetest.ee.main

import android.os.Handler
import android.util.Log
import com.alikazi.codetest.ee.utils.Constants
import com.alikazi.codetest.ee.utils.MockDataHelper
import com.alikazi.codetest.ee.viewmodels.RequestResponseModels

class AppRepository {

    fun sendMessageToServer(request: RequestResponseModels.MessageRequest): RequestResponseModels.MessageResponse {
        Log.d(Constants.LOG_TAG, "message sent to server: " + request.messageSent.text)
        val response = RequestResponseModels.MessageResponse()
        val received = MessageReceived(MockDataHelper.generateRandomString())
        Handler().postDelayed( {
            response._messageReceived.postValue(received)
//            response._networkErrors.postValue("Some exception")
        }, 1000)


        return response
    }
}