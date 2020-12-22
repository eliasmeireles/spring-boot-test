package com.softwareplace.clubhealth.container

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer

class DBContainerTest private constructor() : PostgreSQLContainer<DBContainerTest>("postgres:12.4") {

    var port : Int? = null

    companion object {
        private var containerTest: DBContainerTest? = null
        fun getInstance(): DBContainerTest? {
            if (containerTest == null) {
                containerTest = DBContainerTest()
            }

            return containerTest
        }

        internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
            override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
                TestPropertyValues.of(
                        "spring.datasource.url=" + containerTest!!.jdbcUrl,
                        "spring.datasource.username=" + containerTest!!.username,
                        "spring.datasource.password=" + containerTest!!.password
                ).applyTo(configurableApplicationContext.environment)
            }
        }
    }

    override fun configure() {
        // Disable Postgres driver use of java.util.logging to reduce noise at startup time
        withUrlParam("loggerLevel", "OFF")
        addEnv("POSTGRES_DB", databaseName)
        addEnv("POSTGRES_USER", username)
        addEnv("POSTGRES_PASSWORD", password)
    }


    override fun getJdbcUrl(): String {
        return ("r2dbc:postgresql://" + containerIpAddress + ":" + port
                + "/" + databaseName)
    }

    fun flyWayUrl(): String {
        return ("jdbc:postgresql://" + containerIpAddress + ":" + port
                + "/" + databaseName)
    }

    override fun start() {
        super.start()
        port = getMappedPort(POSTGRESQL_PORT)
        println("Container => URL(${containerTest!!.jdbcUrl})")
        println("Container => username(${containerTest!!.username})")
        println("Container => password(${containerTest!!.password})")

        System.setProperty("spring.datasource.url", containerTest!!.jdbcUrl)
        System.setProperty("spring.datasource.username", containerTest!!.username)
        System.setProperty("spring.datasource.password", containerTest!!.password)
    }


    override fun stop() {

    }
}