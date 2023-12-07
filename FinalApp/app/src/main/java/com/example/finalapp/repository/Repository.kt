package com.example.finalapp.repository

import com.example.finalapp.api.RetrofitInstance
import com.example.finalapp.model.ApiResponse
import com.example.finalapp.model.Asignacion
import com.example.finalapp.model.Candidato
import com.example.finalapp.model.CrearVotacion
import com.example.finalapp.model.VerVotacion
import com.example.finalapp.model.Votacion
import com.example.finalapp.model.Votar
import retrofit2.Response

class Repository {
    suspend fun getVotaciones(ci: String): Response<ApiResponse<List<Votacion>>> {
        return RetrofitInstance.api.getVotaciones(ci)
    }

    suspend fun postVotacion(datos: CrearVotacion): Response<ApiResponse<Votacion>> {
        return RetrofitInstance.api.postVotacion(datos)
    }

    suspend fun getAsignaciones(ci: String): Response<ApiResponse<List<Asignacion>>> {
        return RetrofitInstance.api.getAsignaciones(ci)
    }

    suspend fun getCandidatos(idVotacion: Int): Response<ApiResponse<List<Candidato>>> {
        return RetrofitInstance.api.getCandidatos(idVotacion)
    }

    suspend fun putVotar(idAsignacion: Int, data: Votar): Response<ApiResponse<Asignacion>> {
        return RetrofitInstance.api.putVotar(idAsignacion, data)
    }

    suspend fun getVotacion(idVotacion: Int): Response<ApiResponse<VerVotacion>> {
        return RetrofitInstance.api.getVotacion(idVotacion)
    }
}