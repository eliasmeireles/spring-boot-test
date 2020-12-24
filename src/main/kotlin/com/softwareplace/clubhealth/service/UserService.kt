package com.softwareplace.clubhealth.service

import com.softwareplace.clubhealth.domain.AppUser
import com.softwareplace.clubhealth.repository.AppUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService {

    @Autowired
    private lateinit var appUserRepository: AppUserRepository

    fun findAll(): Flux<AppUser> {
        return appUserRepository.findAll()
    }

    fun findUser(userId: String): Mono<AppUser> {
        return appUserRepository.findById(userId)
    }
}
