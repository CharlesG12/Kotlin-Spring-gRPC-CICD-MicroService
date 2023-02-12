package com.example.demo.config

import com.linecorp.armeria.server.cors.CorsService
import com.linecorp.armeria.common.HttpMethod
import com.linecorp.armeria.server.grpc.GrpcService
import com.linecorp.armeria.server.logging.LoggingService
import com.linecorp.armeria.spring.ArmeriaServerConfigurator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.linecorp.armeria.server.logging.AccessLogWriter
import io.grpc.BindableService
import io.grpc.protobuf.services.ProtoReflectionService

@Configuration
class ArmeriaConfig {

    @Bean
    fun armeriaServerConfigurator(bindableServices: List<BindableService>): ArmeriaServerConfigurator =
        ArmeriaServerConfigurator { builder ->
            builder
                .decorator(LoggingService.builder().newDecorator())
                .decorator(
                    CorsService.builderForAnyOrigin()
                    .allowRequestMethods(HttpMethod.knownMethods())
                    .allowRequestHeaders("Accept", "Accept-Language", "Content-Language", "Content-Type")
                    .newDecorator())
                .accessLogWriter(AccessLogWriter.combined(), false)
                .service(
                    GrpcService.builder()
                        .addServices(bindableServices)
                        .addService(ProtoReflectionService.newInstance())
                        .enableUnframedRequests(true)
                        .build()
                )
        }
}