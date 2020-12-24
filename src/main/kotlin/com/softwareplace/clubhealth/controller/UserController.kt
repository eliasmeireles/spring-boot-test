package com.softwareplace.clubhealth.controller

import com.softwareplace.clubhealth.domain.AppUser
import com.softwareplace.clubhealth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping(value = ["users"])
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun getUsers(): ResponseEntity<Flux<AppUser>> {
        return ResponseEntity.ok(userService.findAll())
    }

    @GetMapping(value = ["{userId}"])
    fun getUser(@PathVariable("userId") userId: String): ResponseEntity<Mono<AppUser>> {
        return ResponseEntity.ok(userService.findUser(userId))
    }
}