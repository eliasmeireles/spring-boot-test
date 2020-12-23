@file:Suppress("RedundantModalityModifier")

package com.softwareplace.clubhealth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.blockhound.BlockHound

@SpringBootApplication
open class ClubhealthApplication

fun main(args: Array<String>) {
	BlockHound.install()
	runApplication<ClubhealthApplication>(*args)
}
