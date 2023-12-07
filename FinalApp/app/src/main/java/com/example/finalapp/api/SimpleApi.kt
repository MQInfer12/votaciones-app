package com.example.finalapp.api

import com.example.finalapp.model.ApiResponse
import com.example.finalapp.model.Asignacion
import com.example.finalapp.model.Candidato
import com.example.finalapp.model.CrearVotacion
import com.example.finalapp.model.VerVotacion
import com.example.finalapp.model.Votacion
import com.example.finalapp.model.Votar
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SimpleApi {
    @GET("misvotaciones/{ci}")
    suspend fun getVotaciones(@Path("ci") ci: String): Response<ApiResponse<List<Votacion>>>

    @POST("crearvotacion")
    suspend fun postVotacion(@Body datos: CrearVotacion): Response<ApiResponse<Votacion>>

    @GET("mispendientes/{ci}")
    suspend fun getAsignaciones(@Path("ci") ci: String): Response<ApiResponse<List<Asignacion>>>

    @GET("candidatos/{idVotacion}")
    suspend fun getCandidatos(@Path("idVotacion") idVotacion: Int): Response<ApiResponse<List<Candidato>>>

    @PUT("votar/{idAsignacion}")
    suspend fun putVotar(@Path("idAsignacion") idAsignacion: Int, @Body data: Votar): Response<ApiResponse<Asignacion>>

    @GET("ver/{idVotacion}")
    suspend fun getVotacion(@Path("idVotacion") idVotacion: Int): Response<ApiResponse<VerVotacion>>
}