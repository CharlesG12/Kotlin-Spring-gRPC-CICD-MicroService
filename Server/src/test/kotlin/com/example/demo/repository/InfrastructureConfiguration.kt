package com.example.demo.repository

import io.r2dbc.h2.H2ConnectionConfiguration
import io.r2dbc.h2.H2ConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

//@Configuration
//@EnableR2dbcRepositories
//@Profile("database")
//class InfrastructureConfiguration : AbstractR2dbcConfiguration() {
//    override fun connectionFactory(): ConnectionFactory {
//        return H2ConnectionFactory(
//            H2ConnectionConfiguration.builder()
//                .url("mem:testdb;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;")
//                .build()
//        )
//    }
//
//    @Bean
//    fun initializer(): ConnectionFactoryInitializer {
//        val initializer = ConnectionFactoryInitializer()
//        initializer.setConnectionFactory(connectionFactory())
//        val databasePopulator = CompositeDatabasePopulator()
//        databasePopulator.addPopulators(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
//        initializer.setDatabasePopulator(databasePopulator)
//        return initializer
//    }
//}