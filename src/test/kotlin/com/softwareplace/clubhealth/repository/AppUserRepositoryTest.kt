package com.softwareplace.clubhealth.repository

import com.softwareplace.clubhealth.container.DBContainerTest
import com.softwareplace.clubhealth.domain.AppUser
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
@ContextConfiguration(initializers = [DBContainerTest.Companion.Initializer::class])
class AppUserRepositoryTest {

    @Autowired
    private lateinit var repository: AppUserRepository

    companion object {
        @ClassRule
        @JvmField
        val container = DBContainerTest.getInstance()
    }

    @Before
    fun `start testContainer database`() {
        container?.start()
    }

    @Test
    fun `must to create user on the database container test`() {
        val appUser = AppUser(userId = null, name = "Elias Meireles", email = "elias@email.com")
        val createdUser: AppUser? = repository.save(appUser).block()
        assertNotNull(createdUser)
    }
}