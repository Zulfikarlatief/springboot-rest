package com.zulfikar.crud.controller

import com.zulfikar.crud.dto.UserData
import com.zulfikar.crud.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users")
class UserController(
    private val userRepository: UserRepository
) {

    @GetMapping("", "/")
    fun getUserList(
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int
        ): List<UserData> {
        return userRepository.getAllUser(page, size)
    }
}