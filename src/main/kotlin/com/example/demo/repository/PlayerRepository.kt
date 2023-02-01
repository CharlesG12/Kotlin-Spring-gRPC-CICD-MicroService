package com.example.demo.repository

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
)

@Repository
interface PlayerRepository : ReactiveCrudRepository<Player, Int>