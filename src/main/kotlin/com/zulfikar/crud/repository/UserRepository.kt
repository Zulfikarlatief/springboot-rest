package com.zulfikar.crud.repository

import com.zulfikar.crud.dto.UserData
import com.zulfikar.crud.model.UserExposed
import org.jetbrains.exposed.sql.selectAll
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
                it[UserExposed.id],
                it[UserExposed.name],
                it[UserExposed.email],
                it[UserExposed.createdAt].millis
            )
        }
    }
}