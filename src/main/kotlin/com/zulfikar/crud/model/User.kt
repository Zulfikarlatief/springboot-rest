package com.zulfikar.crud.model

import org.jetbrains.exposed.sql.CurrentDateTime
import org.jetbrains.exposed.sql.Table


object UserExposed : Table("user") {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 50).nullable()
    val email = varchar("email", 50).nullable()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime())
}