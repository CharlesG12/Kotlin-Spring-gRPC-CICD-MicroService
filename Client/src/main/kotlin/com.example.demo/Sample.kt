package com.example.demo

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.example.demo.grpc.PlayerGrpcClient
import io.grpc.ManagedChannelBuilder
import org.slf4j.LoggerFactory


fun main() {
    val root = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
    root.level = Level.INFO


    val host = "localhost"
    val port = 8080

    // Reactive gRPC
    println("gRPC Demo")
    val channel = ManagedChannelBuilder
        .forAddress(host, port)
        .usePlaintext()
        .build()
    val grpcClient = PlayerGrpcClient(channel)
    println("PlayerGrpcClient fine")
    val grpcPlayers = grpcClient.getAllPlayers()
    println("PlayerGrpcClient fine")
    grpcPlayers.doOnNext { e -> println(e) }.block()
    grpcClient.close()
}