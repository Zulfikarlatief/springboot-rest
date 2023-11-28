package com.zulfikar.crud.repository

import com.zulfikar.crud.model.UserMongo
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface UserMongoRepository : MongoRepository<UserMongo, String> {

    // Spring Data MongoDB will automatically generate the query based on the method name
    fun findBynameNot(name: String): List<UserMongo>

    // You can also use the 'IsNotEmpty' suffix
    fun findBynameIsNotEmpty(): List<UserMongo>
}

@Repository
class CustomUserMongoRepository(private val mongoTemplate: MongoTemplate) {

    fun findById(id: String): UserMongo? {
        val query = Query(Criteria.where("_id").`is`(ObjectId(id)))
        return mongoTemplate.findOne(query, UserMongo::class.java, "user")
    }
}