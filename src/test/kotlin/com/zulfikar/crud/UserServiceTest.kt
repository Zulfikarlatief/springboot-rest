package com.zulfikar.crud

import com.zulfikar.crud.dto.UserData
import com.zulfikar.crud.model.UserMongo
import com.zulfikar.crud.repository.UserMongoRepository
import com.zulfikar.crud.repository.UserRepository
import com.zulfikar.crud.service.UserService
import org.bson.types.ObjectId
import org.joda.time.DateTime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var userMongoRepository: UserMongoRepository


    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun testGetUsers() {
        val page = 1
        val size = 10

        val mockUserDataList = listOf(
            UserData(1, "John Doe", "john@example.com", DateTime.now().millis),
            UserData(2, "Jane Doe", "jane@example.com", DateTime.now().millis),
        )

        `when`(userRepository.getAllUser(page, size)).thenReturn(mockUserDataList)

        val result = userService.getUsers(page, size)
        assertEquals(mockUserDataList, result)
    }

    @Test
    fun testGetUsersMongo() {

        val mockUserDataList = listOf(
            UserMongo("John Doe", "john@example.com"),
            UserMongo("Jane Doe", "jane@example.com"),
        )

        `when`(userMongoRepository.findAll()).thenReturn(mockUserDataList)

        val result = userService.getUsersMongo()
        val expectedRes = mockUserDataList.map { UserData(it) }
        assertEquals(expectedRes, result)
    }
}
