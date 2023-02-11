package com.example.demo
import com.example.demo.repository.Player

fun getPlayers() = listOf(
    Player(1, "Marcus", "Rashford", "marcus.rashford@man.utd"),
    Player(2, "Antonio", "Marshall", "antonio.marshall@man.utd"),
    Player(3, "Luke", "Shaw", "luke.shaw@man.utd")
)

val newPlayer = Player(4, "Harry", "Maguire", "harry.maguire@man.utd")