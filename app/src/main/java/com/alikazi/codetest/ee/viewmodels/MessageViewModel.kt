package com.alikazi.codetest.ee.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MessageViewModel(private val appRepository) : ViewModel() {

    private var messageRequestLiveData = MutableLiveData<RequestResponseModels.MessageRequest>()
    private val messageResponseLiveData = Transformations.map(messageRequestLiveData) {
        RequestResponseModels.MessageResponse() // TODO
    }

    val response = Transformations.switchMap(messageResponseLiveData) {
        it._text
    }

    val networkErrors = Transformations.switchMap(messageResponseLiveData) {
        it._networkErrors
    }

    fun sendMessageToServer(messageRequest: RequestResponseModels.MessageRequest) {
        messageRequestLiveData.postValue(messageRequest)
    }

}