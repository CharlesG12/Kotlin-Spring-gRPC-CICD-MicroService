package com.example.demo.grpc

import com.charles.demo.*
import com.example.demo.service.PlayerService
import io.grpc.Status
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@Service
class PlayerGrpcService(val service: PlayerService
) : ReactorPlayerServiceGrpc.PlayerServiceImplBase() {

    override fun getAllPlayers(request: Mono<GetAllPlayersReq>): Mono<GetAllPlayersRes> {
        return request
            .flatMap {
                service.getAllPlayer()
                    .timeout(Duration.ofMillis(3000))
                    .switchIfEmpty(Flux.empty())
                    .onErrorResume { err ->
                        val grpcErrorStatus = when (err) {
                            is IllegalArgumentException -> Status.INVALID_ARGUMENT.withDescription("<test>")
                            else -> Status.UNKNOWN.withDescription(err.message)
                        }
                        Mono.error(grpcErrorStatus.asRuntimeException())
                    }
                    .map { entity -> entity.toMessage() }
                    .collectList()
                    .map { messages ->
                        GetAllPlayersRes.newBuilder()
                            .addAllPlayers(messages)
                            .build()
                    }
            }
    }
    override fun getPlayerById(request: Mono<GetPlayerByIdReq>): Mono<GetPlayerByIdRes> {
        return request
            .flatMap { r ->
                service.getPlayerById(r.id)
                    .switchIfEmpty(Mono.empty())
                    .onErrorResume { err ->
                        val grpcErrorStatus = when (err) {
                            is IllegalArgumentException -> Status.INVALID_ARGUMENT.withDescription("<test>")
                            else -> Status.UNKNOWN.withDescription(err.message)
                        }
                        Mono.error(grpcErrorStatus.asRuntimeException())
                    }
                    .map { entity -> entity.toMessage()}
                    .map { player ->
                        GetPlayerByIdRes.newBuilder()
                            .setPlayer(player)
                            .build()
                    }
        }
    }



}