package com.example.finalapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalapp.model.ApiResponse
import com.example.finalapp.model.Asignacion
import com.example.finalapp.model.Candidato
import com.example.finalapp.model.Votar
import com.example.finalapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class VotarViewModel(private val repository: Repository): ViewModel() {
    val getResponse: MutableLiveData<Response<ApiResponse<List<Candidato>>>> = MutableLiveData()
    val putResponse: MutableLiveData<Response<ApiResponse<Asignacion>>> = MutableLiveData()

    fun getAsignaciones(idVotacion: Int) {
        viewModelScope.launch {
            val response = repository.getCandidatos(idVotacion)
            getResponse.value = response
        }
    }

    fun putVotar(idAsignacion: Int, data: Votar) {
        viewModelScope.launch {
            val response = repository.putVotar(idAsignacion, data)
            putResponse.value = response
        }
    }
}