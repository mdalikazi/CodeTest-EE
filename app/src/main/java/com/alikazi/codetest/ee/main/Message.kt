package com.alikazi.codetest.ee.main

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var text: String)