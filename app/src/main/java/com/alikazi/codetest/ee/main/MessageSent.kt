package com.alikazi.codetest.ee.main

import java.util.*

data class MessageSent(
    var text: String,
    var timestamp: Date = Date(System.currentTimeMillis())
)