package com.example.soccermatch.data.local.entity

data class Standings(
    val draw: Int,
    val goal: Int,
    val loss: Int,
    val name: String,
    val played: Int,
    val teamId: String,
    val total: Int,
    val win: Int
)