@file:Suppress("RedundantModalityModifier")

package com.softwareplace.clubhealth

import com.softwareplace.clubhealth.repository.ReactivePagingAndSortingRepositoryImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import reactor.blockhound.BlockHound

@SpringBootApplication
@EnableR2dbcRepositories(
        repositoryBaseClass = ReactivePagingAndSortingRepositoryImpl::class
)
open class ClubhealthApplication

fun main(args: Array<String>) {
    BlockHound.install()
    runApplication<ClubhealthApplication>(*args)
}
