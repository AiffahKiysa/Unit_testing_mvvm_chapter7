package com.example.mvvm_car_chapter6.service

import com.example.mvvm_car_chapter6.model.GetAllCarResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    // GET digunakan untuk memanggil semua data yang terdapat pada server
    @GET("admin/car")
    suspend fun getAllCar(): GetAllCarResponse
}