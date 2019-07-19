package com.alikazi.codetest.ee.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alikazi.codetest.ee.main.AppRepository

class EEViewModelFactory(private val appRepository: AppRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessageViewModel::class.java)) {
            return MessageViewModel(appRepository) as T
        }

        throw IllegalArgumentException("There is unknown ViewModel in EEViewModelFactory")
    }
}