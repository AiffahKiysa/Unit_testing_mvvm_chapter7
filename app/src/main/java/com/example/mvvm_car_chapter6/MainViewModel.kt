package com.example.mvvm_car_chapter6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mvvm_car_chapter6.repository.MainRepository
import com.example.mvvm_car_chapter6.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getAllCarData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}