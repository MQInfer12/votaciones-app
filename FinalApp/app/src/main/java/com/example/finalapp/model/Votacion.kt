package com.example.finalapp.model

data class Votacion (
    val id: Int,
    val ci: Int,
    val nombre: String
)

data class CrearVotacion (
    val ci: Int,
    val nombre: String,
    val candidatos: Array<String>,
    val asignaciones: Array<Int>
)

data class VerCandidato (
    val nombre: String,
    val votos: Int
)

data class VerVotacion (
    val totalAsignados: Int,
    val totalVotos: Int,
    val candidatos: Array<VerCandidato>
)