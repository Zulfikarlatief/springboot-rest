package com.zulfikar.crud.service

import com.zulfikar.crud.dto.UserData
import com.zulfikar.crud.model.UserMongo
import com.zulfikar.crud.repository.CustomUserMongoRepository
import com.zulfikar.crud.repository.UserMongoRepository
import com.zulfikar.crud.repository.UserRepository
import org.jetbrains.exposed.exceptions.EntityNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMongoRepository: UserMongoRepository,
    private val customUserMongoRepository: CustomUserMongoRepository
) {

    fun getUsers(page: Int, size: Int): List<UserData> {
        return userRepository.getAllUser(page, size)
    }

    fun addUser(name: String, email: String): UserData {
        val userId = userRepository.addUser(name, email)
        return userRepository.getUserById(userId)
    }

    fun deleteUser(id: Int) {
        userRepository.delete(id)
    }

    fun updateUser(id: Int, name: String, email: String): UserData {
        userRepository.updateUser(id, name, email)
        return userRepository.getUserById(id)
    }

    fun getUsersMongo() : List<UserData> {
        val res = userMongoRepository.findAll()
        return res.map { UserData(it) }
    }

    fun createUserMongo() {
        val user = UserMongo("mongo", "mongo@test.com")
        userMongoRepository.insert(user)
    }

    fun getUserByIdMongo(id: String) : UserData {
        val user = customUserMongoRepository.findById(id) ?: throw NullPointerException()
        return UserData(user)
    }

}