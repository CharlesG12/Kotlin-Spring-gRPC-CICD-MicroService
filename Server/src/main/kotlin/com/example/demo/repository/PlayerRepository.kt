package com.example.demo.repository

import com.charles.demo.PlayerDTO
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Table("player")
data class Player(
    @Id
    var id: Int?,
    @Column("first_name")
    var firstName: String,
    @Column("last_name")
    var lastName: String,
    @Column("email")
    var email: String
) {
// This is for grpc create player
    constructor(dto: PlayerDTO): this(
        if (dto.id > 0) dto.id else null,
        dto.firstName,
        dto.lastName,
        dto.email
    )
    fun toMessage() = PlayerDTO.newBuilder()
        .setId(this.id!!)
        .setFirstName(this.firstName)
        .setLastName(this.lastName)
        .setEmail(this.email)
        .build()!!
}

@Repository
interface PlayerRepository : ReactiveCrudRepository<Player, Int>