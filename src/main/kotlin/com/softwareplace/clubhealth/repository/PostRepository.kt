package com.softwareplace.clubhealth.repository

import com.softwareplace.clubhealth.domain.Post

interface PostRepository : ReactivePagingAndSortingRepository<Post, String>
