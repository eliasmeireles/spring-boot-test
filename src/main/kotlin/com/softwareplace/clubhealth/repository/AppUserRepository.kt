package com.softwareplace.clubhealth.repository

import com.softwareplace.clubhealth.domain.AppUser
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface  AppUserRepository : ReactiveCrudRepository<AppUser, String> {

}