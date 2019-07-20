package com.alikazi.codetest.ee.viewmodels

import androidx.lifecycle.MutableLiveData
import com.alikazi.codetest.ee.main.MessageReceived
import com.alikazi.codetest.ee.main.MessageSent

class RequestResponseModels {

    class MessageRequest(var messageSent: MessageSent)

    class MessageResponse{
        var _messageReceived = MutableLiveData<MessageReceived>()
        var _networkErrors = MutableLiveData<String>()
    }
}