package com.zulfikar.crud.dto

data class UserData(
    var id: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var createdAt: Long? = null
)