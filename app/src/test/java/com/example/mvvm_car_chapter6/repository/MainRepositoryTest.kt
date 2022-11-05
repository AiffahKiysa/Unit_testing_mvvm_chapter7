package com.example.mvvm_car_chapter6.repository

import com.example.mvvm_car_chapter6.model.GetAllCarResponse
import com.example.mvvm_car_chapter6.service.ApiHelper
import com.example.mvvm_car_chapter6.service.ApiService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class MainRepositoryTest {

    private lateinit var service: ApiService
    private lateinit var apiHelper: ApiHelper
    private lateinit var repo: MainRepository
    @Before
    fun setUp(){
        service = mockk()
        apiHelper = ApiHelper(service)
        repo = MainRepository(apiHelper)
    }
    @Test
    fun getUsers(): Unit = runBlocking {
        val respAllCar = mockk<GetAllCarResponse>()

        every {
            runBlocking {
                service.getAllCar()
            }
        } returns respAllCar

        repo.getUsers()

        verify {
            runBlocking { service.getAllCar() }
        }
    }
}