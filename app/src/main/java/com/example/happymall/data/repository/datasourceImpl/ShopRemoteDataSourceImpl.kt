package com.example.happymall.data.repository.datasourceImpl

import com.example.happymall.data.api.ShopApiService
import com.example.happymall.data.model.Cart
import com.example.happymall.data.model.CartItem
import com.example.happymall.data.model.Category
import com.example.happymall.data.model.Login
import com.example.happymall.data.model.LoginResponse
import com.example.happymall.data.model.Shop
import com.example.happymall.data.model.ShopItem
import com.example.happymall.data.repository.datasource.ShopRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class ShopRemoteDataSourceImpl @Inject constructor(
    private val apiService: ShopApiService
) : ShopRemoteDataSource {
    override suspend fun getAllProducts(): Response<Shop> {
        return apiService.getAllProducts()
    }

    override suspend fun getProduct(itemId: Int): Response<ShopItem> {
        return apiService.getProduct(itemId)
    }

    override suspend fun getAllCategories(): Response<Category> {
        return apiService.getAllCategories()
    }

    override suspend fun getCategoryProducts(category: String): Response<Shop> {
        return apiService.getCategoryProducts(category)
    }

    override suspend fun uploadProduct(shopItem: ShopItem): Response<ShopItem> {
        return apiService.uploadProduct(shopItem)
    }

    override suspend fun updateProduct(id: Int, shopItem: ShopItem): Response<ShopItem> {
        return apiService.updateProduct(id, shopItem)
    }

    override suspend fun deleteProduct(id: Int): Response<ShopItem> {
        return apiService.deleteProduct(id)
    }

    override suspend fun getCart(id: Int): Response<Cart> {
        return apiService.getCart(id)
    }

    override suspend fun getCartProducts(id: Int): Response<CartItem> {
        return apiService.getCartProducts(id)
    }

    override suspend fun addToCart(cartItem: CartItem): Response<CartItem> {
        return apiService.addToCart(cartItem)
    }

    override suspend fun updateCart(id: Int, cartItem: CartItem): Response<CartItem> {
        return apiService.updateCart(id, cartItem)
    }

    override suspend fun deleteCart(id: Int): Response<CartItem> {
        return apiService.deleteCart(id)
    }

    override suspend fun loginUser(login: Login): Response<LoginResponse> {
        return apiService.loginUser(login)
    }

}