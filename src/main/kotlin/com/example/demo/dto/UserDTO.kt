package com.example.demo.dto

import com.example.demo.entities.User

sealed class UserDTO {
    abstract val firstName: String?
    abstract val lastName: String?
    abstract var email: String?
    abstract var balance: Long?

    fun toDomainEntity(userId: Long? = null): User { //TODO: step 2- switch to shapeshift
        return User(
            id = userId,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            balance = this.balance
        )
    }
}