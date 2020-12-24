package com.softwareplace.clubhealth.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.convert.R2dbcConverter
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.repository.query.RelationalEntityInformation
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Transactional(readOnly = true)
class ReactivePagingAndSortingRepositoryImpl<T : Any, ID> constructor(
        private val entity: RelationalEntityInformation<T, ID>,
        private val entityTemplate: R2dbcEntityOperations,
        converter: R2dbcConverter
) : SimpleR2dbcRepository<T, ID>(
        entity,
        entityTemplate,
        converter
), ReactivePagingAndSortingRepository<T, ID> {

    override fun exists(criteria: Criteria): Mono<Boolean> {
        return count(criteria)
                .map {
                    it > 0
                }
    }

    override fun count(criteria: Criteria): Mono<Long> {
        return entityTemplate.count(Query.query(criteria), this.entity.javaType)
    }

    @Transactional
    override fun delete(criteria: Criteria): Mono<Long> {
        return this.entityTemplate.delete(Query.query(criteria), this.entity.javaType).map { count ->
            count.toLong()
        }
    }

    override fun findOne(criteria: Criteria): Mono<T> {
        return entityTemplate.selectOne(Query.query(criteria), entity.javaType)
    }

    override fun findAll(criteria: Criteria): Flux<T> {
        return entityTemplate.select(Query.query(criteria), entity.javaType)
    }

    override fun findAll(sort: Sort): Flux<T> {
        val query = Query.empty().sort(sort)

        return entityTemplate.select(query, entity.javaType)
    }

    override fun findAll(sort: Sort, criteria: Criteria): Flux<T> {
        val query = Query.query(criteria).sort(sort)

        return entityTemplate.select(query, entity.javaType)
    }

    override fun findAll(pageable: Pageable): Mono<Page<T>> {
        val query = Query.empty().with(pageable)

        return entityTemplate.select(query, entity.javaType)
                .collectList()
                .zipWith(count()) { items, count ->
                    PageImpl(
                            items,
                            pageable,
                            count
                    )
                }
    }

    override fun findAll(pageable: Pageable, criteria: Criteria): Mono<Page<T>> {
        val query = Query.query(criteria).with(pageable)

        return entityTemplate.select(query, entity.javaType)
                .collectList()
                .zipWith(count(criteria)) { items, count ->
                    PageImpl(
                            items,
                            pageable,
                            count
                    )
                }
    }
}