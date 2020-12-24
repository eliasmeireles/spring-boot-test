package com.softwareplace.clubhealth.controller

import com.softwareplace.clubhealth.domain.Post
import com.softwareplace.clubhealth.entities.UserPost
import com.softwareplace.clubhealth.service.PostService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping(value = ["posts"])
class PostController(val service: PostService) {

    @GetMapping
    suspend fun getPost(
            @RequestParam("page") page: Int?,
            @RequestParam("count") count: Int?,
    ): Page<UserPost> {
        return service.getPosts(page, count)
    }

    @PostMapping
    fun newPost(@RequestBody post: UserPost): Mono<UserPost> {
        return service.newPost(post)
    }
}