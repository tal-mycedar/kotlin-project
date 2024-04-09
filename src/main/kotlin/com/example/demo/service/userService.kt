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
        val userEntity = createUserDTO.let {
            User(null, it.firstName, it.lastName, it.email, it.balance)
        }

        userDao.save(userEntity)

        userEntity.let {
            User(it.id, it.firstName, it.lastName, it.email, it.balance)
        }

        return userEntity
    }

    fun getUsers(pageable: Pageable): Page<User> {
        return userDao.findAll(pageable).map { User(it.id, it.firstName, it.lastName, it.email, it.balance, it.createdAt,
            it.updatedAt) } //TODO: Replace with a transformer
    }

    fun updateUser(userId: Long, updateUserRequest: UpdateUsersDTO): User {

        try {
            val existingUser: User = userDao.findById(userId)
                .orElseThrow { EntityNotFoundException("User with id {userId} not found") }

            objectMapper.updateValue(existingUser, updateUserRequest)

            return userDao.save(existingUser)

        } catch (err: Exception) {
            throw Exception("User update failed with the following error {err}")
        }
    }

    fun deleteUser(userId: Long): Any {
        val user: User = userDao.findById(userId)
            .orElseThrow { EntityNotFoundException("User with id {userId} not found") }

        return userDao.deleteById(userId)
    }

    fun search(getUsersDTO: GetUsersDTO): List<User> {
        return userDao.findUsers(getUsersDTO)
    }
}

//TODO: Exceptions