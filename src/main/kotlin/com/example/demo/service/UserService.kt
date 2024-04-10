package com.example.demo.service

import com.example.demo.dao.UserDao
import com.example.demo.dto.CreateUserDTO
import com.example.demo.dto.GetUsersDTO
import com.example.demo.dto.UpdateUsersDTO
import com.example.demo.entities.User
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService(val userDao: UserDao) {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    fun createUser(createUserDTO: CreateUserDTO): User {
        val userEntity: User = createUserDTO.toDomainEntity()

        return userDao.save(userEntity)
    }

    fun updateUser(userId: Long, updateUserRequest: UpdateUsersDTO): User {
        val existingUser: User = userDao.findById(userId)
            .orElseThrow { EntityNotFoundException("User with id $userId not found") }
        try {
            objectMapper.updateValue(existingUser, updateUserRequest)

            return userDao.save(existingUser)

        } catch (err: Exception) {
            throw Exception("User update failed with the following error $err")
        }
    }

    fun deleteUser(userId: Long): Any {
        val user: User = userDao.findById(userId)
            .orElseThrow { EntityNotFoundException("User with id $userId not found") }

        return userDao.deleteById(userId)
    }

    fun search(pageable: Pageable, getUsersDTO: GetUsersDTO?): Page<User> {
        return userDao.findUsers(pageable, getUsersDTO)
    }
}

//TODO: Exceptions and validations