package com.zulfikar.crud.model

import org.bson.types.ObjectId
import org.jetbrains.exposed.sql.CurrentDateTime
import org.jetbrains.exposed.sql.Table
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*


object UserExposed : Table("user") {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 50).nullable()
    val email = varchar("email", 50).nullable()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime())
}

@Document(collection = "user")
class UserMongo() {

    constructor(name: String, email: String) : this() {
        this.id = ObjectId()
        this.name = name
        this.email = email
    }

    @Id
    lateinit var id: ObjectId
    var name: String? = null
    var email: String? = null
    var createdAt: Date = Date()
}