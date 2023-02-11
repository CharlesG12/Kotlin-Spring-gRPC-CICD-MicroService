package com.example.demo.rest
import com.example.demo.repository.Player
import com.example.demo.service.PlayerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("player")
class PlayerController(val service: PlayerService) {

    @GetMapping
    fun getAllPlayers(): Flux<Player> {
        return service.getAllPlayer()
    }

    @GetMapping("{playerId}")
    fun getPlayerById(@PathVariable playerId: Int): Mono<ResponseEntity<Any>> {
        return Mono.just(1)
            .map { ResponseEntity.ok<Any>(service.getPlayerById(playerId)) }
            .switchIfEmpty(Mono.just(ResponseEntity.badRequest().body(Error("Error"))))
    }

    @PostMapping
    fun createPlayer(@RequestBody playerMono: Mono<Player>): Mono<Player> {
        return playerMono.flatMap(service::createPlayer)
    }

    @PutMapping("{playerId}")
    fun updatePlayer(@PathVariable playerId: Int,
                       @RequestBody playerMono: Mono<Player>
    ): Mono<Player> {
        return playerMono.flatMap { player -> service.updatePlayer(playerId, player) }
    }

    @DeleteMapping("{playerId}")
    fun deletePlayer(@PathVariable playerId: Int): Mono<Void> {
        return service.deletePlayer(playerId)
    }
}