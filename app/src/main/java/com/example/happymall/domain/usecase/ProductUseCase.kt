package com.example.happymall.domain.usecase

import com.example.happymall.data.model.Category
import com.example.happymall.data.model.Shop
import com.example.happymall.data.util.Resource
import com.example.happymall.domain.repository.ShopRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun getAllProducts(): Resource<Shop> {
        return repository.getAllProducts()
    }

    suspend fun getAllCategories(): Resource<Category> {
        return repository.getAllCategories()
    }

    suspend fun getCategoryProducts(category: String): Resource<Shop> {
        return repository.getCategoryProducts(category)
    }

}