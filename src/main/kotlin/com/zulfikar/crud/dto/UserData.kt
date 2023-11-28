package com.zulfikar.crud.dto

import com.zulfikar.crud.model.UserMongo

data class UserData(
    var id: String? = null,
    var name: String? = null,
    var email: String? = null,
    var createdAt: Long? = null
) {
    constructor(user: UserMongo) : this(
        user.id.toString(),
        user.name,
        user.email,
        user.createdAt.time
    )
}