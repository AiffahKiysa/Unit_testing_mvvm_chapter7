package com.example.mvvm_car_chapter6.repository

import com.example.mvvm_car_chapter6.service.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getAllCarData()
}