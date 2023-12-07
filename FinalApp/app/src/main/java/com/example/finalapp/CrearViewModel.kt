package com.example.finalapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalapp.model.ApiResponse
import com.example.finalapp.model.CrearVotacion
import com.example.finalapp.model.Votacion
import com.example.finalapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class CrearViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<ApiResponse<Votacion>>> = MutableLiveData()

    fun postVotacion(datos: CrearVotacion) {
        viewModelScope.launch {
            val response = repository.postVotacion(datos)
            myResponse.value = response
        }
    }
}