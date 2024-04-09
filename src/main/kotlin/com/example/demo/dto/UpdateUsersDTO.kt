package com.example.demo.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class UpdateUsersDTO(
    @JsonProperty("firstName")
    val firstName: String?,

    @JsonProperty("lastName")
    val lastName: String?,

    @JsonProperty("email")
    var email: String?,

    @JsonProperty("balance")
    var balance: Long?
)