package com.example.happymall.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.happymall.data.model.Category
import com.example.happymall.data.model.Shop
import com.example.happymall.data.util.Network.isNetworkAvailable
import com.example.happymall.data.util.Resource
import com.example.happymall.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val productUseCase: ProductUseCase
) : AndroidViewModel(app) {

    val products: MutableLiveData<Resource<Shop>> = MutableLiveData()
    val categories: MutableLiveData<Resource<Category>> = MutableLiveData()

    fun getAllCategories() = viewModelScope.launch(IO) {
        categories.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = productUseCase.getAllCategories()
                categories.postValue(apiResult)
            } else {
                categories.postValue(Resource.Error(message = "No Internet"))
            }
        } catch (e: Exception) {
            categories.postValue(Resource.Error(message = "${e.localizedMessage} ?: Unknown Error"))
        }
    }

    fun getAllProducts() = viewModelScope.launch(IO) {
        products.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = productUseCase.getAllProducts()
                products.postValue(apiResult)
            } else {
                products.postValue(Resource.Error(message = "Internet not available"))
            }
        } catch (e: Exception) {
            products.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }

    fun getCategoryProducts(category: String) = viewModelScope.launch(IO) {
        if (category != "All") {
            products.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    val apiResult = productUseCase.getCategoryProducts(category)
                    products.postValue(apiResult)
                } else {
                    products.postValue(Resource.Error(message = "Internet not available"))
                }
            } catch (e: Exception) {
                products.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
            }
        } else {
            getAllProducts()
        }
    }

}