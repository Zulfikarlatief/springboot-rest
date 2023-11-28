package com.zulfikar.crud.repository

import com.zulfikar.crud.dto.UserData
import com.zulfikar.crud.model.UserExposed
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class UserRepository {

    @Transactional
    fun getAllUser(page: Int, size: Int): List<UserData> {
        return UserExposed.slice(
            UserExposed.id,
            UserExposed.name,
            UserExposed.email,
            UserExposed.createdAt
        ).selectAll(
        ).limit(
            size, offset = (page - 1) * size
        ).map {
            UserData(
                it[UserExposed.id].toString(),
                it[UserExposed.name],
                it[UserExposed.email],
                it[UserExposed.createdAt].millis
            )
        }
    }

    @Transactional
    fun getUserById(id: Int): UserData {
        return UserExposed.slice(
            UserExposed.id,
            UserExposed.name,
            UserExposed.email,
            UserExposed.createdAt
        ).select { UserExposed.id eq id }.map {
            UserData(
                it[UserExposed.id].toString(),
                it[UserExposed.name],
                it[UserExposed.email],
                it[UserExposed.createdAt].millis
            )
        }.single()
    }

    @Transactional
    fun addUser(name: String, email: String): Int {
        val userId = UserExposed.insert {
            it[UserExposed.name] = name
            it[this.email] = email
            it[createdAt] = DateTime()
        }.get(UserExposed.id)
        return userId
    }

    @Transactional
    fun updateUser(id: Int, name: String, email: String) {
        UserExposed.update({ UserExposed.id eq id }) {
            it[UserExposed.name] = name
            it[UserExposed.email] = email
        }
    }

    @Transactional
    fun delete(id: Int) {
        UserExposed.deleteWhere { UserExposed.id eq id }
    }
}