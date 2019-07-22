package com.alikazi.codetest.ee.utils

import kotlin.random.Random

class MockDataHelper {

    companion object {
        fun generateRandomString(): String {
            val length = Random.nextInt(2, 100)
            val alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz "
            return (1 .. length)
                .map { alphabets.random() }
                .joinToString("")
        }

        fun generateValidPhoneNumber(): String {
            val numbers = "0123456789"
            return (1 .. Constants.PHONE_NUMER_MINIMUM_LENGTH)
                .map { numbers.random() }
                .joinToString("")
        }

        fun generateInvalidPhoneNumber(): String {
            val numbers = "0123456789"
            val length = Constants.PHONE_NUMER_MINIMUM_LENGTH - Random.nextInt(1,9)
            return (1 .. length)
                .map { numbers.random() }
                .joinToString("")
        }
    }
}