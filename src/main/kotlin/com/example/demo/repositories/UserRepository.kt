package com.example.demo.repositories

import com.example.demo.dto.GetUsersDTO
import com.example.demo.entities.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserRepository {
    fun findUsers(pageable: Pageable, getUsersDTO: GetUsersDTO?): Page<User>
}