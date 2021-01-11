package com.softwareplace.clubhealth.service

import com.softwareplace.clubhealth.domain.PostImage
import com.softwareplace.clubhealth.entities.UserPost
import com.softwareplace.clubhealth.repository.PostImageRepository
import com.softwareplace.clubhealth.repository.PostRepository
import com.softwareplace.clubhealth.service.PageHelper.Companion.pageRequest
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class PostService(val repository: PostRepository, val postImageRepository: PostImageRepository) {

    suspend fun getPosts(page: Int?, count: Int?): Page<UserPost> {
        val pageResponse = repository.findAll(pageable = pageRequest(page = page, count = count))
            .awaitFirst()

        val content: ArrayList<UserPost> = arrayListOf()

        pageResponse.content.forEach { post ->
            val element: MutableList<PostImage> =  postImageRepository.findAllByPostId(postId = post.postId!!)
                .collectList().awaitFirst()

            content.add(UserPost(appUserId = post.appUserId, content = post.content, images = element.map { it.imageUrl }))
        }
        return  PageImpl(content, pageResponse.pageable, pageResponse.totalElements)
    }

    fun newPost(userPost: UserPost): Mono<UserPost> {
        return repository.save(UserPost.post(userPost)).flatMap { storedPost ->
            val postImages = postImageRepository
                .saveAll(PostImage.build(postId = storedPost.postId!!, userPost = userPost))
                .collectList()

            Mono.just(storedPost)
                .zipWith(postImages)
                .map { userPost }
        }
    }
}
