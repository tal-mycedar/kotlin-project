package com.example.demo.controller

import com.example.demo.dto.CreateUserDTO
import com.example.demo.dto.GetUsersDTO
import com.example.demo.dto.UpdateUsersDTO
import com.example.demo.entities.User
import com.example.demo.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/users")
class UserController(private val userService: UserService) {

    @PostMapping("/search")
    fun search(@RequestBody getUsersDTO: GetUsersDTO): List<User> {
        return userService.search(getUsersDTO)
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody createUserDTO: CreateUserDTO): User {
        return userService.createUser(createUserDTO)
    }

    @GetMapping()
    fun getUsers(pageable: Pageable, @RequestBody(required = false) getUsersDTO: GetUsersDTO?): Page<User> {
        return userService.getUsers(pageable)
    }

    @PatchMapping("/{userId}")
    fun updateUser(@PathVariable("userId") userId : Long, @RequestBody(required = false) updateUserDTO: UpdateUsersDTO): User {
        return userService.updateUser(userId, updateUserDTO)
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable("userId") userId : Long): Any {
        return userService.deleteUser(userId)
    }
}



/*
* create repo + commit current version
* try to add pagination to the search method
* add exceptions
* add validation
* findAll - add transformer
* */