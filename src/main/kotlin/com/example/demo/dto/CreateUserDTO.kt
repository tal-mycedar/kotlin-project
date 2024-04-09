package com.example.demo.dto

class CreateUserDTO(
    val firstName: String,
    val lastName: String,
    var email: String,
    var balance: Long? = 0
)