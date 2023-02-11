package com.example.demo.grpc

import com.charles.demo.*
import io.grpc.ManagedChannel
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.io.Closeable
import java.util.concurrent.TimeUnit

class PlayerGrpcClient(private val channel: ManagedChannel) : Closeable {
    private val playerStub: ReactorPlayerServiceGrpc.ReactorPlayerServiceStub by lazy {
        ReactorPlayerServiceGrpc.newReactorStub(channel)
    }

    fun getAllPlayers(): Mono<List<PlayerDTO>> {
        val req = GetAllPlayersReq.newBuilder().build().toMono()
        return playerStub.getAllPlayers(req).map { it.playersList }
    }

    fun getPlayerById(id: Int): Mono<PlayerDTO> {
        val req = GetPlayerByIdReq.newBuilder().setId(id).build()
        return playerStub.getPlayerById(req).map { it.player }
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }

}