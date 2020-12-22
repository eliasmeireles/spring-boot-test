@file:Suppress("RedundantModalityModifier")

package com.softwareplace.clubhealth.config

import com.softwareplace.clubhealth.container.DBContainerTest
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import javax.sql.DataSource

@Configuration
@EnableR2dbcRepositories
open class DatabaseConnectionTest : AbstractR2dbcConfiguration() {

    @Bean
    @Throws(NullPointerException::class)
    open fun containerInstance(): DBContainerTest {
        return DBContainerTest.getInstance()!!
    }

    @Bean
    open fun getDataSource(): DataSource? {
        val dbContainerTest = DBContainerTest.getInstance()!!
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.postgresql.Driver")
        dataSourceBuilder.url(dbContainerTest.flyWayUrl())
        dataSourceBuilder.username(dbContainerTest.username)
        dataSourceBuilder.password(dbContainerTest.password)
        return dataSourceBuilder.build()
    }

    @Bean
    open override fun connectionFactory(): ConnectionFactory {
        val dbContainerTest = DBContainerTest.getInstance()!!
        return PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(dbContainerTest.containerIpAddress)
                        .port(dbContainerTest.port!!)
                        .username(dbContainerTest.username)
                        .password(dbContainerTest.password)
                        .database(dbContainerTest.databaseName)
                        .build());
    }
}