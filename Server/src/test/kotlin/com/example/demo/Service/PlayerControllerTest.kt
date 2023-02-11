package com.example.demo.Service

import com.example.demo.getPlayers
import com.example.demo.newPlayer
import com.example.demo.repository.PlayerRepository
import com.example.demo.service.PlayerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import com.ninjasquad.springmockk.MockkBean
import org.junit.jupiter.api.Test
import io.mockk.every
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import reactor.test.StepVerifier
import org.assertj.core.api.Assertions.assertThat
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@SpringBootTest
class PlayerControllerTest(@Autowired val service: PlayerService)  {

    @MockkBean
    private lateinit var repo: PlayerRepository

    @Test
    fun testGetAllPlayers() {
        every { repo.findAll() } returns getPlayers().toFlux()

        StepVerifier.create(service.getAllPlayer())
            .recordWith { mutableListOf() }
            .thenConsumeWhile { true }
            .consumeRecordedWith { players ->
                assertThat(players.size).isEqualTo(getPlayers().size)
            }.verifyComplete()
    }

    @Test
    fun testGetPlayerById() {
        val id = 2
        every { repo.findById(id) } returns getPlayers()[id-1].toMono()

        StepVerifier.create(service.getPlayerById(id))
            .consumeNextWith { employee ->
                assertThat(employee.firstName).isEqualTo("Antonio")
            }.verifyComplete()
    }

    @Test
    fun testCreatePlayer() {
        every { repo.save(newPlayer) } returns newPlayer.toMono()

        StepVerifier.create(service.createPlayer(newPlayer))
            .consumeNextWith { employee ->
                assertThat(employee.firstName).isEqualTo("Harry")
            }.verifyComplete()
    }

    @Test
    fun testUpdateEmployee() {
        val emp = getPlayers()[0]
        val newEmail = "blah"
        emp.email = newEmail

        every { repo.findById(emp.id!!) } returns getPlayers()[(emp.id!!)-1].toMono()
        every { repo.save(emp) } returns emp.toMono()

        StepVerifier.create(service.updatePlayer(emp.id!!, emp))
            .consumeNextWith { player ->
                assertThat(player.email).isEqualTo(newEmail)
            }.verifyComplete()
    }

    @Test
    fun testDeletePlayer() {
        val id = 1
        every { repo.deleteById(id) } returns Mono.empty()

        StepVerifier.create(service.deletePlayer(1)).verifyComplete()
    }

}