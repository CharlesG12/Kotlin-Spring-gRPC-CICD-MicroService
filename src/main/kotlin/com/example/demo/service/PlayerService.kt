package com.example.demo.service

import com.example.demo.repository.Player
import com.example.demo.repository.PlayerRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PlayerService(val repo: PlayerRepository) {
    fun getAllPlayer(): Flux<Player> {
        return repo.findAll()
    }

    fun getPlayerById(playerId: Int): Mono<Player> {
        return repo.findById(playerId)
    }

    fun createPlayer(player: Player): Mono<Player> {
        return repo.save(player)
    }

    fun updatePlayer(playerId: Int, player: Player): Mono<Player> {
        return repo.findById(playerId)
            .map { row ->
                row.firstName = player.firstName
                row.lastName = player.lastName
                row.email = player.email
                row
            }
            .flatMap(repo::save)
    }

    fun deletePlayer(id: Int): Mono<Void> {
        return repo.deleteById(id)
    }
}