package com.alikazi.codetest.ee.viewmodels

import androidx.lifecycle.MutableLiveData

class RequestResponseModels {

    class MessageRequest(var text: String)

    class MessageResponse{
        var _text = MutableLiveData<String>()
        var _networkErrors = MutableLiveData<String>()
    }
}