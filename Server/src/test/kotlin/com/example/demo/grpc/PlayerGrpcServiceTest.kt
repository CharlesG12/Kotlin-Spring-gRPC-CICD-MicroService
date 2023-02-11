package com.example.demo.grpc

import com.charles.demo.GetPlayerByIdReq
import com.example.demo.getPlayers
import com.example.demo.repository.PlayerRepository
import com.example.demo.service.PlayerService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import reactor.test.StepVerifier
import reactor.kotlin.core.publisher.toMono
import org.assertj.core.api.Assertions.assertThat

@SpringBootTest
class PlayerGrpcServiceTest(@Autowired val grpcService: PlayerGrpcService) {

    @MockkBean
    private lateinit var service: PlayerService
    @MockkBean
    private lateinit var repo: PlayerRepository

    @Test
    fun testGetEmployeeById() {
        val id = 2
        val req = GetPlayerByIdReq.newBuilder().setId(id).build().toMono()
        every { service.getPlayerById(id) } returns getPlayers()[id-1].toMono()

        StepVerifier.create(grpcService.getPlayerById(req))
            .consumeNextWith { res ->
                assertThat(res.player.firstName).isEqualTo("Antonio")
            }.verifyComplete()
    }

}