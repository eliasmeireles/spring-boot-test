package com.softwareplace.clubhealth.repository

import com.softwareplace.clubhealth.domain.PostImage
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PostImageRepository : ReactiveCrudRepository<PostImage, String> {

    fun findAllByPostId(postId: String): Flux<PostImage>

    fun saveAll(postImages: MutableList<PostImage>): Flux<PostImage>
}