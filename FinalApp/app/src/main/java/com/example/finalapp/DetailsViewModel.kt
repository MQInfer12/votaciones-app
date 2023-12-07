package com.example.finalapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalapp.model.ApiResponse
import com.example.finalapp.model.VerVotacion
import com.example.finalapp.model.Votacion
import com.example.finalapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<ApiResponse<VerVotacion>>> = MutableLiveData()

    fun getVotacion(idVotacion: Int) {
        viewModelScope.launch {
            val response = repository.getVotacion(idVotacion)
            myResponse.value = response
        }
    }
}