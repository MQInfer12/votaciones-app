package com.example.finalapp.model

data class Asignacion (
    val id: Int,
    val ci: Int,
    val idVotacion: Int,
    val idCandidato: Int?,
    val Votacion: Votacion
)

data class Votar (
    val idCandidato: Int
)