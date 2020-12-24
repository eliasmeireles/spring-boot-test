package com.softwareplace.clubhealth.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Table(value = "app_user")
data class AppUser(

        @Id
        val userId: String?,

        @NotNull
        @NotEmpty(message = "User name can't be null!")
        val name: String,

        @NotNull
        @NotEmpty(message = "User email can't be null!")
        val email: String,
)