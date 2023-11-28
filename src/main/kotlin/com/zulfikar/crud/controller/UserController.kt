package com.zulfikar.crud.controller

import com.zulfikar.crud.dto.UserData
import com.zulfikar.crud.service.UserService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping("", "/")
    fun getUserList(
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int
        ): List<UserData> {
        return userService.getUsers(page, size)
    }

    @PostMapping("/")
    fun createUser(@RequestBody form: UserForm): UserData {
        return userService.addUser(form.name, form.email)
    }

    @PutMapping("/{id}")
    fun updateUser(@RequestBody form: UserForm, @PathVariable id: Int): UserData {
        return userService.updateUser(id, form.name, form.email)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): Boolean {
        userService.deleteUser(id)
        return true
    }

    @GetMapping("/mongo",)
    fun getMongoUserList(
    ): List<UserData> {
        return userService.getUsersMongo()
    }

    @GetMapping("/mongo/{id}",)
    fun getMongoUser(
        @PathVariable id: String
    ): UserData {
        return userService.getUserByIdMongo(id)
    }

    @PostMapping("/mongo",)
    fun createUserMongo(
        @RequestBody form: UserForm
    ) {
        userService.createUserMongo()
    }

}

data class UserForm(
    var name: String,
    var email: String
)
