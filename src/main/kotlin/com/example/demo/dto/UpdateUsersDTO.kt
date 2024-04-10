package com.example.demo.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class UpdateUsersDTO(
    override val firstName: String?,

    override val lastName: String?,

    override var email: String?,

    override var balance: Long?
): UserDTO()