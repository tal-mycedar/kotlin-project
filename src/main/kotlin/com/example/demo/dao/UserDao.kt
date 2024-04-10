package com.example.demo.dao

import com.example.demo.entities.User
import com.example.demo.repositories.UserRepository
import org.springframework.data.jpa.repository.JpaRepository

interface UserDao: JpaRepository<User, Long>, UserRepository {}