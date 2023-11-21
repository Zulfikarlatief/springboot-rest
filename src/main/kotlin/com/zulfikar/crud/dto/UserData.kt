package com.zulfikar.crud.dto

import com.zulfikar.crud.model.User

data class UserData(
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    var createdAt: Long? = null
) {
    constructor(user: User) : this(
        user.id,
        user.name,
        user.email,
        user.createdAt
    )
}