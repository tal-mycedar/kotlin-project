package com.example.demo.entities

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "users")
open class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("firstName")
    var firstName: String? = "",

    @JsonProperty("lastName")
    var lastName: String? = "",

    @JsonProperty("email")
    var email: String? = "",

    @JsonProperty("balance")
    var balance: Long? = 0,

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @JsonProperty("createdAt")
    val createdAt: Instant? = null,

    @UpdateTimestamp
    @Column(nullable = false)
    @JsonProperty("updatedAt")
    var updatedAt: Instant? = null
)