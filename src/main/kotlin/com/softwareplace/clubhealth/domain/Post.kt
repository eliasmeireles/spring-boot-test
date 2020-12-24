package com.softwareplace.clubhealth.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value = "post")
data class Post(
        @Id
        val postId: String?,
        val appUserId: String,
        val content: String,
)
