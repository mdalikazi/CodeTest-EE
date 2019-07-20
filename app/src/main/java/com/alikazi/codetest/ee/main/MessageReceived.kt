package com.alikazi.codetest.ee.main

import java.util.*

data class MessageReceived(
    var text: String,
    var timestamp: Date = Date(System.currentTimeMillis())
)