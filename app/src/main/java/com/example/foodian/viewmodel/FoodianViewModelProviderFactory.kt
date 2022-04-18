package com.example.foodian.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodian.data.DataSource
import com.example.foodian.data.MenuItem
import com.example.foodian.reprository.FoodianRepository
import java.lang.IllegalArgumentException

class FoodianViewModelProviderFactory(val repository: FoodianRepository): ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FoodianViewModel::class.java)) {
            return FoodianViewModel(repository) as T
        } else throw IllegalArgumentException("Unknown ViewModel Class")
    }
}