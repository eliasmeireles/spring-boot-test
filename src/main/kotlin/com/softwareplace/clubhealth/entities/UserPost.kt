package com.softwareplace.clubhealth.entities

import com.softwareplace.clubhealth.domain.Post
import com.softwareplace.clubhealth.domain.PostImage
import reactor.util.function.Tuple2

class UserPost(
        val appUserId: String,
        val content: String,
        val images: List<String>
) {
    companion object {
        fun fromPostTuple(tuple: Tuple2<Post, MutableList<PostImage>>) = UserPost(appUserId = tuple.t1.appUserId, content = tuple.t1.content, images = tuple.t2.map { it.imageUrl })

        fun post(userPost: UserPost): Post = Post(postId = null, appUserId = userPost.appUserId, content = userPost.content)
    }
}