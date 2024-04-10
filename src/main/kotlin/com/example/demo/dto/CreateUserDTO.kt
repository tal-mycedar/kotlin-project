package com.example.demo.dto

class CreateUserDTO(
    override val firstName: String,
    override val lastName: String,
    override var email: String?,
    override var balance: Long? = 0
): UserDTO()