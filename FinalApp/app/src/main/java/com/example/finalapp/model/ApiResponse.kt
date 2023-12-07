package com.example.finalapp.model

data class ApiResponse<T> (
    val message: String,
    val data: T
)