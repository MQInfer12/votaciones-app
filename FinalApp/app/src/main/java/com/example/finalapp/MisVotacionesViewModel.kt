package com.example.finalapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalapp.model.ApiResponse
import com.example.finalapp.model.Asignacion
import com.example.finalapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MisVotacionesViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<ApiResponse<List<Asignacion>>>> = MutableLiveData()

    fun getAsignaciones(ci: String) {
        viewModelScope.launch {
            val response = repository.getAsignaciones(ci)
            myResponse.value = response
        }
    }
}