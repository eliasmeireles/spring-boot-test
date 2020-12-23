package com.softwareplace.clubhealth.exception

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono
import java.util.*

@Component
@Order(-2)
class GlobalExceptionHandler(
        errorAttributes: ErrorAttributes,
        webResourceProperties: WebProperties.Resources,
        applicationContext: AbstractApplicationContext,
        serverCodecConfigurer: ServerCodecConfigurer,
) : AbstractErrorWebExceptionHandler(errorAttributes, webResourceProperties, applicationContext) {

    init {
        this.setMessageWriters(serverCodecConfigurer.writers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes?): RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.all(), this::formatError)
    }

    private fun formatError(request: ServerRequest): Mono<ServerResponse> {
//        Enable stacktrace
//        val errorAttributes: MutableMap<String, Any> = getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE))

        val errorAttributes: MutableMap<String, Any> = getErrorAttributes(request, ErrorAttributeOptions.defaults())
        val statusError: Int = Optional.ofNullable(errorAttributes["status"]).orElse(500) as Int
        return ServerResponse.status(statusError)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorAttributes))
    }
}