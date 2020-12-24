package com.softwareplace.clubhealth.service

import com.softwareplace.clubhealth.domain.Post
import com.softwareplace.clubhealth.domain.PostImage
import com.softwareplace.clubhealth.entities.UserPost
import com.softwareplace.clubhealth.repository.PostImageRepository
import com.softwareplace.clubhealth.repository.PostRepository
import com.softwareplace.clubhealth.service.PageHelper.Companion.pageRequest
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PostService(val repository: PostRepository, val postImageRepository: PostImageRepository) {

    fun getPosts(page: Int?, count: Int?): Mono<Page<Post>> {
        return  repository.findAll(pageable = pageRequest(page = page, count = count))
//        val posts = repository.findAll(pageable = Pageable.unpaged())
//        return posts.flatMap { page ->
//            page.content.map { post ->
//                Mono.just(post.content)
//                    .zipWith(postImageRepository.findAllByPostId(postId = post.postId!!).collectList())
//                    .map { tuple -> UserPost.fromPostTuple(tuple) }
//            }
//        }
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
