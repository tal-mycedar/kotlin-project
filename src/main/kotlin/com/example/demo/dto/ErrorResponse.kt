package com.example.demo.dto

import java.time.LocalDateTime

data class ErrorResponse (
    val title: String? = "An Error occurred",
    val message: String? = "",
    val status: Int = 500,
    val dateTime: LocalDateTime? = LocalDateTime.now()
)