package com.example.demo.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
open class GetUsersDTO(
    @JsonProperty("firstName")
    val firstName: String?,

    @JsonProperty("lastName")
    val lastName: String?,

    @JsonProperty("email")
    var email: String?,

    @JsonProperty("balance")
    var balance: Long?,

    @JsonProperty("createdAt")
    var createdAt: LocalDateTime?,

    @JsonProperty("updatedAt")
    var updatedAt: LocalDateTime?
)