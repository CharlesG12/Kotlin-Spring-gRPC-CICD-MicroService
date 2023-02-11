package com.example.demo.config

import com.linecorp.armeria.server.cors.CorsService
import com.linecorp.armeria.common.HttpMethod
import com.linecorp.armeria.server.logging.LoggingService
import com.linecorp.armeria.spring.ArmeriaServerConfigurator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.linecorp.armeria.server.logging.AccessLogWriter

@Configuration
class ArmeriaConfig {

    @Bean
    fun armeriaServerConfigurator(): ArmeriaServerConfigurator =
        ArmeriaServerConfigurator { builder ->
            builder
                .decorator(LoggingService.builder().newDecorator())
                .decorator(
                    CorsService.builderForAnyOrigin()
                    .allowRequestMethods(HttpMethod.knownMethods())
                    .allowRequestHeaders("Accept", "Accept-Language", "Content-Language", "Content-Type")
                    .newDecorator())
                .accessLogWriter(AccessLogWriter.combined(), false)
        }
}