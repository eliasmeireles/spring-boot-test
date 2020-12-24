package com.softwareplace.clubhealth.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort


class PageHelper {

    companion object {
        const val DEFAULT_COUNT: Int = 10
        fun pageRequest(page: Int?, count: Int?): PageRequest {
            return PageRequest.of(when (page) {
                null -> 0
                else -> page
            }, when (count) {
                null -> DEFAULT_COUNT
                else -> count
            })
        }

        fun pageRequest(page: Int?, count: Int?, sort: Sort): PageRequest {
            return PageRequest.of(when (page) {
                null -> 0
                else -> page
            }, when (count) {
                null -> DEFAULT_COUNT
                else -> count
            }, sort)
        }

        fun stringQueryParamBuild(value: String?): String {
            return when (value) {
                null -> "%%"
                else -> "%$value%"
            }
        }
    }
}