package com.softwareplace.clubhealth.domain

import com.softwareplace.clubhealth.entities.UserPost
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value = "post_images")
data class PostImage(
        @Id
        val postImageId: String?,
        val postId: String,
        val imageUrl: String
) {
    companion object {
        fun build(postId: String, userPost: UserPost) = userPost.images.map { image: String -> PostImage(postImageId = null, postId = postId, imageUrl = image) }
    }

    override fun toString(): String {
        return "PostImage(postImageId=$postImageId, postId='$postId', imageUrl='$imageUrl')"
    }
}