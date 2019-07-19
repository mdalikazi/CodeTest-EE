package com.alikazi.codetest.ee.utils

import com.alikazi.codetest.ee.main.AppRepository
import com.alikazi.codetest.ee.viewmodels.EEViewModelFactory

object Injector {

    fun provideViewModelFactory(): EEViewModelFactory {
        return EEViewModelFactory(provideAppRepository())
    }

    private fun provideAppRepository(): AppRepository {
        return AppRepository()
    }

}