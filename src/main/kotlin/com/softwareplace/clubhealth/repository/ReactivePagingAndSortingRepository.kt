package com.softwareplace.clubhealth.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@NoRepositoryBean
interface ReactivePagingAndSortingRepository<T : Any, ID> : ReactiveCrudRepository<T, ID> {
    fun count(criteria: Criteria): Mono<Long>
    fun exists(criteria: Criteria): Mono<Boolean>
    fun delete(criteria: Criteria): Mono<Long>
    fun findOne(criteria: Criteria): Mono<T>
    fun findAll(criteria: Criteria): Flux<T>
    fun findAll(sort: Sort): Flux<T>
    fun findAll(sort: Sort, criteria: Criteria): Flux<T>
    fun findAll(pageable: Pageable): Mono<Page<T>>
    fun findAll(pageable: Pageable, criteria: Criteria): Mono<Page<T>>
}