package com.example.demo.grpc

import com.charles.demo.*
import io.grpc.ManagedChannel
import reactor.core.publisher.Mono
import java.io.Closeable
import java.util.concurrent.TimeUnit

class PlayerGrpcClient(private val channel: ManagedChannel) : Closeable {
    private val employeeStub: ReactorPlayerServiceGrpc.ReactorPlayerServiceStub by lazy {
        ReactorPlayerServiceGrpc.newReactorStub(channel)
    }

    fun getPlayerById(id: Int): Mono<PlayerDTO> {
        val req = GetPlayerByIdReq.newBuilder().setId(id).build()
        return employeeStub.getPlayerById(req).map { it.player }
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }

}