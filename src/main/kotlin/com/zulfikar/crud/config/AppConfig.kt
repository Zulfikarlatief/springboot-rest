package com.zulfikar.crud.config

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader


@Configuration
class AppConfig(private val resourceLoader: ResourceLoader) {

    @Bean
    fun config(): JsonObject {
        val resource: Resource = resourceLoader.getResource("classpath:application.json")
        return Gson().fromJson(resource.inputStream.reader(), JsonObject::class.java)
    }
}