package com.example.mvvm_car_chapter6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_car_chapter6.databinding.ActivityMainBinding
import com.example.mvvm_car_chapter6.model.GetAllCarResponse
import com.example.mvvm_car_chapter6.model.GetAllCarResponseItem
import com.example.mvvm_car_chapter6.service.ApiClient
import com.example.mvvm_car_chapter6.service.ApiHelper
import com.example.mvvm_car_chapter6.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(ApiHelper(ApiClient.instance))
        )[MainViewModel::class.java]

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getAllCarData().observe(this@MainActivity) { resources ->
            when (resources.status) {
                Status.LOADING -> {
                    // Handle ketika data loading
                    binding.pbLoading.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    // Handle ketika data success
                    showRecyclerList(resources.data)
                    binding.pbLoading.visibility = View.GONE
                }
                Status.ERROR -> {
                    // Handle ketika data error
                    binding.pbLoading.visibility = View.GONE
                }
            }
        }
    }

    private fun showRecyclerList(data: GetAllCarResponse?) {
        val adapter = MainAdapter(object : MainAdapter.OnClickListener {
            override fun onClickItem(data: GetAllCarResponseItem) {
                Toast.makeText(this@MainActivity, "Menekan ${data.name}", Toast.LENGTH_SHORT).show()
            }
        })

        adapter.submitData(data)
        binding.rvList.adapter = adapter
    }
}