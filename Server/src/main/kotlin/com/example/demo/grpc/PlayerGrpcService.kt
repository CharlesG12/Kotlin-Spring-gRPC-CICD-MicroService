package com.example.demo.grpc

import com.charles.demo.*
import com.example.demo.service.PlayerService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PlayerGrpcService(val service: PlayerService
) : ReactorPlayerServiceGrpc.PlayerServiceImplBase() {

    override fun getPlayerById(request: Mono<GetPlayerByIdReq>): Mono<GetPlayerByIdRes> {
        return request
            .flatMap { r ->
                service.getPlayerById(r.id)
                    .switchIfEmpty(Mono.empty())
                    .map { entity -> entity.toMessage()}
                    .map { player ->
                        GetPlayerByIdRes.newBuilder()
                            .setPlayer(player)
                            .build()
                    }
        }
    }



}