package com.example.mvvm_car_chapter6.service

class ApiHelper(private val apiService: ApiService) {

    suspend fun getAllCarData() = apiService.getAllCar()
}