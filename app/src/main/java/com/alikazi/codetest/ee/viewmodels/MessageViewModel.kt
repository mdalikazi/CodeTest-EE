package com.alikazi.codetest.ee.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alikazi.codetest.ee.main.AppRepository
import com.alikazi.codetest.ee.main.MessageReceived

class MessageViewModel(private val appRepository: AppRepository) : ViewModel() {

    private var messageRequestLiveData = MutableLiveData<RequestResponseModels.MessageRequest>()
    private val messageResponseLiveData = Transformations.map(messageRequestLiveData) {
        appRepository.sendMessageToServer(it)
    }

    val messageReceived: LiveData<MessageReceived> =
        Transformations.switchMap(messageResponseLiveData) {
            it._messageReceived
    }

    val networkErrors: LiveData<String> =
        Transformations.switchMap(messageResponseLiveData) {
            it._networkErrors
    }

    fun sendMessageToServer(messageRequest: RequestResponseModels.MessageRequest) {
        messageRequestLiveData.postValue(messageRequest)
    }

}