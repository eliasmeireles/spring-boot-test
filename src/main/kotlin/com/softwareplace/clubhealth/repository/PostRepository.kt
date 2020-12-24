package com.softwareplace.clubhealth.repository

import com.softwareplace.clubhealth.domain.Post
import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Mono

interface PostRepository : ReactivePagingAndSortingRepository<Post, String> {

//    override fun findAll(pageable: Pageable) : Flow<Page<Post>>
}
