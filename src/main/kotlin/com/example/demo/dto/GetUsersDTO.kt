package com.example.demo.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
open class GetUsersDTO(
    override val firstName: String?,

    override val lastName: String?,

    override var email: String?,

    override var balance: Long?,

    var createdAt: LocalDateTime?,

    var updatedAt: LocalDateTime?
): UserDTO()