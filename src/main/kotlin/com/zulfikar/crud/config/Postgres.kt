package com.zulfikar.crud.config

import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class Postgres {

    @Bean
    fun dataSource(): DataSource {
        return HikariDataSource().apply {
            jdbcUrl = "jdbc:postgresql://localhost:5432/zulfikar"
            username = "zulfikar"
            password = "developer"
            driverClassName = "org.postgresql.Driver"
            maximumPoolSize = 5
            // additional HikariCP properties if needed
        }
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        return SpringTransactionManager(dataSource())
    }
}