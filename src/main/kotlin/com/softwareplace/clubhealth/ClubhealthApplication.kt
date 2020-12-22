@file:Suppress("RedundantModalityModifier")

package com.softwareplace.clubhealth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ClubhealthApplication

fun main(args: Array<String>) {
	runApplication<ClubhealthApplication>(*args)
}
