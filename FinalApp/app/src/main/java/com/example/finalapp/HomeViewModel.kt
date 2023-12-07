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

class HomeViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<ApiResponse<List<Votacion>>>> = MutableLiveData()

    fun getVotaciones(ci: String) {
        viewModelScope.launch {
            val response = repository.getVotaciones(ci)
            myResponse.value = response
        }
    }
}